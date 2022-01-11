package com.example.hier.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.hier.models.MeetingroomReservation
import com.example.hier.models.Reservation

@Dao
interface ReservationDao {

    @Transaction
    @Query("select * from meetingroomreservation")
    fun getAllMeetingRoomReservations(): LiveData<List<MeetingroomReservation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMeetingRoomReservations(list: List<MeetingroomReservation>)

    @Transaction
    @Query("select * from meetingroomreservation where `from`=:date")
    fun getMeetingRoomReservationsOnDate(date: Long): LiveData<List<MeetingroomReservation>>

    @Transaction
    @Query("select * from reservation where `customer`=:customer")
    fun getAllReservations(customer: String): LiveData<List<Reservation>>
}
