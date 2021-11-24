package com.example.hier.network

import android.util.Log
import com.example.hier.util.Resource
import retrofit2.Response

abstract class BaseDataSource {
    //TODO delete logging
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                Log.e("GetResult", body.toString())
                if (body != null)
                    return Resource.success(body)
            }
            Log.e("getResult", "response not successful: ${response.raw()}")
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Log.e("getResult", "exception thrown: ${e.message ?: e.toString()}")
            Log.e("getresult", "stacktrace: ${e.stackTraceToString()}")
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call failed for the following reason: $message", data = null)
    }
}