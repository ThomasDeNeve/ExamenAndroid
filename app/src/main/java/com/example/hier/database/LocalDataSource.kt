package com.example.hier.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hier.models.Reservation
import com.example.hier.models.Room
import com.example.hier.network.LoginResponse
import com.example.hier.networkModels.ReservationNetworkModel
import com.example.hier.networkModels.RoomNetworkModel

class LocalDataSource(private val roomDao: RoomDao, private val userDao: UserDao, private val reservationDao: ReservationDao) {
    fun getRooms() = roomDao.getAllRooms()

    fun getRoom(id: Int) = roomDao.getRoom(id)

    // save list of rooms to database
    fun saveRooms(list: List<RoomNetworkModel>) {
        val roomList = ArrayList<Room>()
        list.forEach { room -> roomList.add(room.toDataBaseModel()) }
        roomDao.insertAll(roomList)
    }

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


}