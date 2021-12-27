package com.example.hier.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
fun <T, A> fetchAndSaveRooms(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
) {
    GlobalScope.launch {
        databaseQuery.invoke().map { Resource.success(it) }
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
            Log.i("DataAccessStrategy", "Successfully fetched data from API")
        } else if (responseStatus.status == Status.ERROR) {
            Log.e("DataAccessStrategy", "Error fetching result from API")
            throw Exception("Exception thrown because response failed: ${responseStatus.message!!}")
        }
    }
}