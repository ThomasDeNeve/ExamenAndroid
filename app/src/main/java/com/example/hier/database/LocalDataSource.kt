package com.example.hier.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hier.models.Location
import com.example.hier.models.LocationWithRooms
import com.example.hier.models.Reservation
import com.example.hier.models.Room
import com.example.hier.network.LoginResponse
import com.example.hier.networkModels.ReservationNetworkModel
import com.example.hier.networkModels.RoomNetworkModel

class LocalDataSource(private val locationDao: LocationDao, private val userDao: UserDao, private val reservationDao: ReservationDao, private val roomDao: RoomDao) {
    fun getLocations() = locationDao.getAllLocations()
    fun getAllRooms() = roomDao.getAllRooms()
    fun getRoomById(roomId: Int) = roomDao.getRoom(roomId)
    fun getLocationById(locationId: Int) = roomDao.getLocation(locationId)
    fun getLocationIdByName(name: String) = locationDao.getLocationIdByName(name)



    // save list of rooms to database
    /*fun saveRooms(list: List<RoomNetworkModel>) {
        val roomList = ArrayList<Room>()
        list.forEach { room -> roomList.add(room.toDataBaseModel()) }
        roomDao.insertAll(roomList)
    }*/

    fun getUser(username: String): LiveData<LoginResponse> {
        return MutableLiveData(LoginResponse(false, "", userDao.getUser(username)))
    }

    fun saveUser(loginResponse: LoginResponse) {
        userDao.insert(loginResponse.user)
    }

    fun getReservations() = reservationDao.getAllReservations()

    fun saveReservations(list: List<ReservationNetworkModel>) {
        val reservationList = ArrayList<Reservation>()
        list.forEach { res -> reservationList.add(res.toDataBaseModel()) }
        reservationDao.insertAll(reservationList)
    }

    fun saveLocations(list: List<LocationWithRooms>) {
        val locations = ArrayList<Location>()
        val rooms = ArrayList<Room>()
        for(lwr in list) {
            locations.add(lwr.location)
            rooms.addAll(lwr.rooms)
        }
        locationDao.insertAll(locations)
        roomDao.insertAll(rooms)
    }


}