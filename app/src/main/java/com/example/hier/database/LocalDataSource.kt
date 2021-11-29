package com.example.hier.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hier.models.Location
import com.example.hier.models.LocationWithRooms
import com.example.hier.models.Reservation
import com.example.hier.models.Room
import com.example.hier.network.LoginResponse
import com.example.hier.networkModels.LocationNetworkModel
import com.example.hier.networkModels.ReservationNetworkModel

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

    fun getUser(username: String): LiveData<LoginResponse> {
        return MutableLiveData(LoginResponse(false, "", userDao.getUser(username)))
    }

    fun saveUser(loginResponse: LoginResponse) {
        userDao.insert(loginResponse.user)
    }

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
}