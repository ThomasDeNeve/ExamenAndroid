package com.example.hier.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hier.models.Location
import com.example.hier.models.Room

@Dao
interface RoomDao {

    @Transaction
    @Query("select * from rooms")
    fun getRooms(): LiveData<List<Room>>

    @Transaction
    @Query("select * from rooms where numberOfSeats=:numberofseats")
    fun getRoomsByNeededSeats(numberofseats: Int): LiveData<List<Room>>

    @Transaction
    @Query("select * from rooms where id=:id")
    fun getRoom(id: Int): LiveData<Room>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Room>)

    // Delete all meetingrooms in the cached database. This ensures the cached data is equal to the data retreived via the API
    @Transaction
    @Query("delete from rooms")
    fun deleteAllRooms()

    @Transaction
    @Query("select * from location where id=:locationId")
    fun getLocation(locationId: Int): LiveData<Location>
}
