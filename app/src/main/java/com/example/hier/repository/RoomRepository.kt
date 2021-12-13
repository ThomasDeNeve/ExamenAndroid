package com.example.hier.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.hier.database.LocalDataSource
import com.example.hier.models.Room
import com.example.hier.network.MeetingroomsGetModel
import com.example.hier.network.RemoteDataSource
import com.example.hier.network.ReservationPostModel
import com.example.hier.networkModels.LocationNetworkModel
import com.example.hier.util.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RoomRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    /*fun getRooms(): LiveData<Resource<List<Room>>> {
        //fetching locations and rooms
        try {
            getLocations()
        } catch (e: Exception) {
            return localDataSource.getRooms()
                .map { Resource.error("Failed to load data from server", it) }
        }
        return localDataSource.getRooms().map { Resource.success(it) }
    }*/

    fun getRooms(neededseats:Int, locationid: Int, datetimeStart: String, datetimeEnd: String): LiveData<Resource<List<Room>>>
    {
        try
        {
            getAvailableRooms(neededseats, locationid, datetimeStart, datetimeEnd)
        }
        catch (e: Exception)
        {
            Log.e("API error", e.message.toString())
            return localDataSource.getRooms().map { Resource.error("Failed to load data from server", it)}
        }
        return localDataSource.getRooms().map { Resource.success(it)}
    }

    fun getRoomById(roomId: Int) = localDataSource.getRoomById(roomId)

    /*fun getLocations() = fetchAndSaveLocations(
        databaseQuery = { localDataSource.getLocations() },
        networkCall = { remoteDataSource.getLocations() },
        saveCallResult = { localDataSource.saveLocations(it) }
    )*/

    fun getAvailableRooms(neededseats:Int, locationid: Int, datetimeStart: String,datetimeEnd: String) = fetchAndSaveRooms(
        databaseQuery = { localDataSource.getRooms()},
        networkCall = { remoteDataSource.getAvailableMeetingrooms(neededseats, locationid, datetimeStart, datetimeEnd)},
        saveCallResult = { localDataSource.saveRooms(it)}
    )

    /*fun getLocations_old() = performGetOperation(
        databaseQuery = { localDataSource.getLocations() },
        networkCall = { remoteDataSource.getLocations() },
        saveCallResult = { localDataSource.saveLocations(it) }
    )*/

    fun getLocationById(locationId: Int) = localDataSource.getLocationById(locationId)

    //TODO: change to remoteDataSource
    fun getLocationIdByName(name: String): Int {
        return localDataSource.getLocationIdByName(name)
    }

    suspend fun addReservation(reservationPostModel: ReservationPostModel){
        remoteDataSource.addReservation(reservationPostModel)
    }

    /*suspend fun getAvailableRooms(locationId: Int, numberOfSeats:Int, datetime:String) : LiveData<Resource<List<Room>>>
    {
        return remoteDataSource.getAvailableMeetingrooms(locationId, numberOfSeats, datetime).map{Resource.success(it)}
    }*/

    fun getRooms_fetchDirectly(): LiveData<Resource<List<Room>>> {
        val rooms = ArrayList<Room>()
        runBlocking {
            launch {
                var error = remoteDataSource.getLocations().message
                if (!error.isNullOrBlank()) {
                    Log.e("Roomrepository", "failed because $error")
                }
                val resource: Resource<List<LocationNetworkModel>> = remoteDataSource.getLocations()
                val list = resource.data!!
                for (it in list) {
                    rooms.addAll(it.meetingRooms)
                }
            }
        }
        val resource: Resource<List<Room>>
        resource = Resource(Status.SUCCESS, rooms, null)
        return MutableLiveData<Resource<List<Room>>>(resource)
    }
}