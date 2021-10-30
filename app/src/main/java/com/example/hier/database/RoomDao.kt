//package com.example.hier.database
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.example.hier.models.Room
//
//@Dao
//interface RoomDao {
//
//    @Transaction
//    @Query("select * from rooms")
//    fun getAllRooms(): LiveData<List<Room>>
//
//    @Transaction
//    @Query("select * from rooms where roomId=:id")
//    fun getRoom(id: Int): LiveData<Room>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(list: List<Room>)
//
//}