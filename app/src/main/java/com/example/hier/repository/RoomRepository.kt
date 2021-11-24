package com.example.hier.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.hier.database.LocalDataSource
import com.example.hier.models.Room
import com.example.hier.network.RemoteDataSource
import com.example.hier.networkModels.LocationNetworkModel
import com.example.hier.util.Resource
import com.example.hier.util.Status
import com.example.hier.util.performGetOperation
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RoomRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getRooms_fetchDirectly(): LiveData<Resource<List<Room>>> {
        val rooms = ArrayList<Room>()
        runBlocking {
            launch {
                var error = remoteDataSource.getLocations().message
                if(!error.isNullOrBlank()){
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

    fun getRooms(): LiveData<Resource<List<Room>>> {
        //fetching locations and rooms
        getLocations()
        return localDataSource.getAllRooms().map { Resource.success(it) }
    }

    fun getRoomById(roomId: Int) = localDataSource.getRoomById(roomId)

    fun getLocations() = performGetOperation(
        databaseQuery = { localDataSource.getLocations() },
        networkCall = { remoteDataSource.getLocations() },
        saveCallResult = { localDataSource.saveLocations(it) }
    )

    fun getLocationById(locationId: Int) = localDataSource.getLocationById(locationId)

    /*fun getLocations() : LiveData<Resource<List<LocationWithRooms>>>{
        var locationsFromDB = localDataSource.getLocations().map { Resource.success(it) }
        var locationsFromAPI = remoteDataSource.getLocations()
        if(locationsFromAPI.status == Status.SUCCESS){
            localDataSource.saveLocations(locationsFromAPI.data!!.toList())
            locationsFromDB = localDataSource.getLocations().map { Resource.success(it) } //update records with new
        }
        return locationsFromDB
    }*/

    fun getLocationIdByName(name: String): Int {
        return localDataSource.getLocationIdByName(name)
    }
}