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
import com.example.hier.util.Resource
import com.example.hier.util.Status
import com.example.hier.util.fetchAndSaveLocations
import com.example.hier.util.performGetOperation
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RoomRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getRooms(): LiveData<Resource<List<Room>>> {
        //fetching locations and rooms
        try {
            getLocations()
        } catch (e: Exception) {
            return localDataSource.getAllRooms()
                .map { Resource.error("Failed to load data from server", it) }
        }
        return localDataSource.getAllRooms().map { Resource.success(it) }
    }

    fun getRoomById(roomId: Int) = localDataSource.getRoomById(roomId)

    fun getLocations() = fetchAndSaveLocations(
        databaseQuery = { localDataSource.getLocations() },
        networkCall = { remoteDataSource.getLocations() },
        saveCallResult = { localDataSource.saveLocations(it) }
    )

    fun getAvailableRooms(locationId:Int, numberOfSeats: Int, datetime: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllRooms()},
        networkCall = { remoteDataSource.getAvailableMeetingrooms(MeetingroomsGetModel(locationId, numberOfSeats, datetime))},
        saveCallResult = { localDataSource.saveRooms(it)}
    )

    fun getLocations_old() = performGetOperation(
        databaseQuery = { localDataSource.getLocations() },
        networkCall = { remoteDataSource.getLocations() },
        saveCallResult = { localDataSource.saveLocations(it) }
    )

    fun getLocationById(locationId: Int) = localDataSource.getLocationById(locationId)

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