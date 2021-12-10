package com.example.hier.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.hier.models.Location
import com.example.hier.models.LocationWithRooms
import com.example.hier.models.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray

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
fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>, networkCall: suspend () -> Resource<A>, saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO)
    {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS)
        {
            saveCallResult(responseStatus.data!!)
            Log.i("DataAccessStrategy", "Successfully fetched data from API")
        }
        else if (responseStatus.status == Status.ERROR)
        {
            Log.e("DataAccessStrategy", "Error fetching result from API")
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

/*fun <T,A> fetchAndSaveMeetingrooms(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
) {
    GlobalScope.launch {
        val source = databaseQuery.invoke().map { Resource.success(it) }
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
            Log.i("DataAccessStrategy", "Successfully fetched data from API")
        } else if (responseStatus.status == Status.ERROR) {
            Log.e("DataAccessStrategy", "Error fetching result from API")
            throw Exception("Exception thrown because response failed: ${responseStatus.message!!}")
        }
    }
}*/

fun <T, A> fetchAndSaveLocations(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
) {
    GlobalScope.launch {
        val source = databaseQuery.invoke().map { Resource.success(it) }
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

fun <T,A> fetchAndSaveRooms(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
){
    GlobalScope.launch {
        val source = databaseQuery.invoke().map { Resource.success(it) }
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

fun parseJson(jsonString: String): ArrayList<LocationWithRooms> {
    val jsonArray = JSONArray(jsonString)

    val locations: ArrayList<LocationWithRooms> = ArrayList()

    //iterate over each location object
    for (i in 0 until jsonArray.length()) {
        val locationObject = jsonArray.getJSONObject(i)
        val rooms: ArrayList<Room> = ArrayList()
        val roomsArray = locationObject.getJSONArray("meetingRooms")

        //iterate over each room object within location
        for (j in 0 until roomsArray.length()) {
            val roomObject = roomsArray.getJSONObject(j)
            val tempRoom = Room(
                roomObject.get("id") as Int,
                roomObject.get("name").toString(),
                roomObject.get("numberOfSeats") as Int,
                roomObject.get("priceEvening").toString().toDouble(),
                roomObject.get("priceFullDay").toString().toDouble(),
                roomObject.get("priceHalfDay").toString().toDouble(),
                roomObject.get("priceTwoHours").toString().toDouble(),
                locationObject.get("id") as Int
            )
            rooms.add(tempRoom)
        }

        val location = LocationWithRooms(
            Location(
                locationObject.get("id") as Int,
                locationObject.get("name").toString(),
                locationObject.get("street").toString(),
                locationObject.get("number") as Int,
                locationObject.get("postalCode").toString(),
                locationObject.get("place").toString()
            ),
            rooms
        )
        locations.add(location)
    }
    return locations
}

/*fun <T, A> fetchUser(networkCall: suspend () -> Resource<A>, saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
            Log.i("DataAccessStrategy", "Successfully fetched data from API")
        } else if (responseStatus.status == Status.ERROR) {
            Log.e("DataAccessStrategy", "Error fetching result from API")
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }*/

