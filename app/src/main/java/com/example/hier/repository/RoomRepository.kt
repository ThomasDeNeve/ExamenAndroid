package com.example.hier.repository

import com.example.hier.database.LocalDataSource
import com.example.hier.models.Room
import com.example.hier.network.RemoteDataSource
import com.example.hier.util.Resource
import com.example.hier.util.Status
import com.example.hier.util.performGetOperation

class RoomRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getRooms() : Resource<List<Room>>{
        var list = listOf<Room>(Room(0,"Vanvoor",200.0,145.0, 145.0,0.0,7),
        Room(1,"Ginder",360.0,275.0,275.0,0.0,40))
        return Resource(Status.SUCCESS, list, null)
    }
    /** TODO: Get real rooms from dtb
     * fun getRooms() = performGetOperation(
        databaseQuery = { localDataSource.getRooms() },
        networkCall = { remoteDataSource.getRooms() },
        saveCallResult = { localDataSource.saveRooms(it.records) }
    )**/
}