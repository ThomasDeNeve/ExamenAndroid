package com.example.hier.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.map

/**
 * Helper method to do 3 things:
 * 1) Fetch all parking objects currently in the database
 * 2) Fetch all parking objects from the API
 * 3) Update the database with the refreshed parking objects
 *
 * @param databaseQuery A function to fetch the data from the databaae (returns LiveData<T>)
 * @param networkCall A suspend function the fetch the data from the API (returns Resource<A>)
 * @param saveCallResult A suspend function to save the newly fetched data to the database
 * @return LiveData<Resource<T>>
 * */
fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
            Log.i("DataAccessStrategy", "Sucessfully fetched data from API")
        } else if (responseStatus.status == Status.ERROR) {
            Log.e("DataAccessStrategy", "Error fetching result from API")
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }