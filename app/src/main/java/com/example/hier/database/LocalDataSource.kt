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
    // fun getLocations() = locationDao.getAllLocations()
    fun getRooms() = roomDao.getRooms()
    // fun getRoomsByNeededSeats(neededseats:Int) = roomDao.getRoomsByNeededSeats(neededseats)

    fun getRoomById(roomId: Int) = roomDao.getRoom(roomId)
    fun getLocationById(locationId: Int) = roomDao.getLocation(locationId)
    fun getLocationIdByName(name: String) = locationDao.getLocationIdByName(name)

    fun getReservations() = reservationDao.getAllMeetingRoomReservations()
    fun getReservations(date: Long) = reservationDao.getMeetingRoomReservationsOnDate(date)

    fun getUser(username: String) = userDao.getUser(username)

    fun saveUser(user: User) {
        userDao.insert(user)

        userDao.getUser(user.username)
    }

/*    fun saveReservations(list: List<CoworkReservationPostModel>) {
        val reservationList = ArrayList<Reservation>()
        list.forEach { res -> reservationList.add(res.toDataBaseModel()) }
        reservationDao.insertAll(reservationList)
    }*/

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
    fun saveRooms(list: List<MeetingRoomNetworkModel>) {
        val rooms = ArrayList<Room>()

        val mappedList: List<Room> = list.map { item -> item.toDatabaseModel() }

        rooms.addAll(mappedList)

        // Delete all rooms in the cached database Before filling it with data retrieved from the api. This ensures we have the updated data
        roomDao.deleteAllRooms()
        roomDao.insertAll(rooms)
    }
}
