package com.example.hier.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.hier.database.LocalDataSource
import com.example.hier.models.Location
import com.example.hier.models.LocationWithRooms
import com.example.hier.models.Room
import com.example.hier.network.RemoteDataSource
import com.example.hier.util.Resource
import com.example.hier.util.Status
import com.example.hier.util.performGetOperation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getRooms() : LiveData<Resource<List<Room>>> {
        //fetching locations and rooms
        getLocations()
        return localDataSource.getAllRooms().map { Resource.success(it) }
    }

    fun getRoomById(roomId: Int) = localDataSource.getRoomById(roomId)

    /*fun getLocations() = performGetOperation(
        databaseQuery = { localDataSource.getLocations() },
        networkCall = { remoteDataSource.getLocations() },
        saveCallResult = { localDataSource.saveLocations(it) }
    )*/

    fun getLocations() : LiveData<Resource<List<LocationWithRooms>>>{
        var locationsFromDB = localDataSource.getLocations().map { Resource.success(it) }
        var locationsFromAPI = remoteDataSource.getLocations()
        if(locationsFromAPI.status == Status.SUCCESS){
            localDataSource.saveLocations(locationsFromAPI.data!!.toList())
            locationsFromDB = localDataSource.getLocations().map { Resource.success(it) } //update records with new
        }
        return locationsFromDB
    }

    /** TODO: Get real rooms from dtb
     * fun getRooms() = performGetOperation(
        databaseQuery = { localDataSource.getRooms() },
        networkCall = { remoteDataSource.getRooms() },
        saveCallResult = { localDataSource.saveRooms(it.records) }
    )**/
}