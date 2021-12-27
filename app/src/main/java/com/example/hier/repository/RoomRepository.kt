package com.example.hier.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.hier.database.LocalDataSource
import com.example.hier.models.Room
import com.example.hier.models.User
import com.example.hier.network.RemoteDataSource
import com.example.hier.networkModels.MeetingroomReservationPostModel
import com.example.hier.util.Resource
import com.example.hier.util.fetchAndSaveRooms
import kotlinx.coroutines.DelicateCoroutinesApi

class RoomRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    @DelicateCoroutinesApi
    fun getRooms(
        neededseats: Int,
        locationid: Int,
        datetimeStart: String,
        datetimeEnd: String
    ): LiveData<Resource<List<Room>>> {
        try {
            getAvailableRooms(neededseats, locationid, datetimeStart, datetimeEnd)
        } catch (e: Exception) {
            Log.e("API error", e.message.toString())
            return localDataSource.getRooms()
                .map { Resource.error("Failed to load data from server", it) }
        }
        return localDataSource.getRooms().map { Resource.success(it) }
    }

    fun getRoomById(roomId: Int) = localDataSource.getRoomById(roomId)

    @DelicateCoroutinesApi
    fun getAvailableRooms(
        neededseats: Int,
        locationid: Int,
        datetimeStart: String,
        datetimeEnd: String
    ) = fetchAndSaveRooms(
        databaseQuery = { localDataSource.getRooms() },
        networkCall = {
            remoteDataSource.getAvailableMeetingrooms(
                neededseats,
                locationid,
                datetimeStart,
                datetimeEnd
            )
        },
        saveCallResult = { localDataSource.saveRooms(it) }
    )

    // TODO: change to remoteDataSource
    fun getLocationIdByName(name: String): Int {
        return localDataSource.getLocationIdByName(name)
    }

    suspend fun addReservation(meetingroomReservationPostModel: MeetingroomReservationPostModel) {
        remoteDataSource.addMeetingroomReservation(meetingroomReservationPostModel)
    }

    suspend fun getNewestUser(): User {
        return localDataSource.getNewestUser()
    }
}
