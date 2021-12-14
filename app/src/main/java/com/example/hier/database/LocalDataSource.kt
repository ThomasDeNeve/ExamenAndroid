package com.example.hier.database

import com.example.hier.models.*
import com.example.hier.networkModels.LocationNetworkModel
import com.example.hier.networkModels.MeetingRoomNetworkModel
import com.example.hier.networkModels.ReservationNetworkModel
import com.example.hier.networkModels.UserNetworkModel

class LocalDataSource(
    private val locationDao: LocationDao,
    private val userDao: UserDao,
    private val reservationDao: ReservationDao,
    private val roomDao: RoomDao
) {
    fun getLocations() = locationDao.getAllLocations()
    fun getAllRooms() = roomDao.getAllRooms()
    fun getRoomById(roomId: Int) = roomDao.getRoom(roomId)
    fun getLocationById(locationId: Int) = roomDao.getLocation(locationId)
    fun getLocationIdByName(name: String) = locationDao.getLocationIdByName(name)

    fun getReservations() = reservationDao.getAllReservations()

    fun getUser(username: String) = userDao.getUser(username)

    fun saveReservations(list: List<ReservationNetworkModel>) {
        val reservationList = ArrayList<Reservation>()
        list.forEach { res -> reservationList.add(res.toDataBaseModel()) }
        reservationDao.insertAll(reservationList)
    }

    fun saveLocations(list: List<LocationNetworkModel>) {
        val locations = ArrayList<Location>()
        val rooms = ArrayList<Room>()
        val lwrList: List<LocationWithRooms> = list.map { item -> item.toDatabaseModel() }
        for (lwr in lwrList) {
            locations.add(lwr.location)
            rooms.addAll(lwr.rooms)
        }
        locationDao.insertAll(locations)
        roomDao.insertAll(rooms)
    }

    fun saveRooms(list: List<MeetingRoomNetworkModel>)
    {
        val rooms = ArrayList<MeetingRoomNetworkModel>()
        rooms.addAll(list)
    }
}