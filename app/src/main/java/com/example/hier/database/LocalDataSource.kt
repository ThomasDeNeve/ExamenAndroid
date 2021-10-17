package com.example.hier.database

import com.example.hier.models.Room
import com.example.hier.networkModels.RoomNetworkModel

class LocalDataSource(private val roomDao: RoomDao) {
    fun getRooms() = roomDao.getAllRooms()

    fun getRoom(id: Int) = roomDao.getRoom(id)

    // save list of rooms to database
    fun saveRooms(list: List<RoomNetworkModel>) {
        val roomList = ArrayList<Room>()
        list.forEach { room -> roomList.add(room.toDataBaseModel()) }
        roomDao.insertAll(roomList)
    }
}