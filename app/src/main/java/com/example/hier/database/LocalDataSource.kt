package com.example.hier.database

import com.example.hier.models.Room
import com.example.hier.models.User
import com.example.hier.networkModels.MeetingRoomNetworkModel

class LocalDataSource(
    private val locationDao: LocationDao,
    private val userDao: UserDao,
    private val reservationDao: ReservationDao,
    private val roomDao: RoomDao
) {
    fun getRooms() = roomDao.getRooms()
    fun getRoomById(roomId: Int) = roomDao.getRoom(roomId)
    fun getLocationIdByName(name: String) = locationDao.getLocationIdByName(name)

    suspend fun getUser(username: String): User {
        return userDao.getUser(username)
    }

    suspend fun getNewestUser(): User {
        return userDao.getNewestUser()
    }

    suspend fun saveUser(user: User) {
        userDao.insert(user)

        userDao.getUser(user.username)
    }

    /*
    * Yves
    * Takes a list of MeetingRoomNetworkModel objects (received through the API)
    * and maps it to a Room object (to be saved in the local database)
    * */
    fun saveRooms(list: List<MeetingRoomNetworkModel>) {
        val rooms = ArrayList<Room>()

        val mappedList: List<Room> = list.map { item -> item.toDatabaseModel() }

        rooms.addAll(mappedList)

        // Delete all rooms in the cached database Before filling it with data retrieved from the api. This ensures we have the updated data
        roomDao.deleteAllRooms()
        roomDao.insertAll(rooms)
    }
}
