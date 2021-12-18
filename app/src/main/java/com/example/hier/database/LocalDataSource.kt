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
    //fun getLocations() = locationDao.getAllLocations()
    fun getRooms() = roomDao.getRooms()
    //fun getRoomsByNeededSeats(neededseats:Int) = roomDao.getRoomsByNeededSeats(neededseats)

    fun getRoomById(roomId: Int) = roomDao.getRoom(roomId)
    fun getLocationById(locationId: Int) = roomDao.getLocation(locationId)
    fun getLocationIdByName(name: String) = locationDao.getLocationIdByName(name)

    fun getReservations() = reservationDao.getAllReservations()

    fun getUser(username: String): LiveData<LoginResponse> {
        return MutableLiveData(LoginResponse(false, "", userDao.getUser(username)))
    }

    fun saveUser(loginResponse: LoginResponse)
    {
        userDao.insert(loginResponse.user)

        userDao.getUser(username)
    }

    fun saveReservations(list: List<ReservationNetworkModel>) {
        val reservationList = ArrayList<Reservation>()
        list.forEach { res -> reservationList.add(res.toDataBaseModel()) }
        reservationDao.insertAll(reservationList)
    }

    /*fun saveLocations(list: List<LocationNetworkModel>)
    {
        val locations = ArrayList<Location>()
        val rooms = ArrayList<Room>()

        val lwrList: List<LocationWithRooms> = list.map { item -> item.toDatabaseModel() }
        for (lwr in lwrList) {
            locations.add(lwr.location)
            rooms.addAll(lwr.rooms)
        }
        locationDao.insertAll(locations)
        roomDao.insertAll(rooms)
    }*/

    /*
    * Yves
    * Takes a list of MeetingRoomNetworkModel objects (received through the API)
    * and maps it to a Room object (to be saved in the local database)
    * */
    fun saveRooms(list: List<MeetingRoomNetworkModel>)
    {
        val rooms = ArrayList<Room>()

        val mappedList: List<Room> = list.map { item -> item.toDatabaseModel() }

        rooms.addAll(mappedList)

        //Delete all rooms in the cached database Before filling it with data retreived from the api. This ensures we have the updated data
        roomDao.deleteAllRooms()
        roomDao.insertAll(rooms)
    }
}