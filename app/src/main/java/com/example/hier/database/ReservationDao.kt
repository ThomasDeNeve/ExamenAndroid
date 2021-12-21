package com.example.hier.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hier.models.Reservation

@Dao
interface ReservationDao {

    @Transaction
    @Query("select * from reservations")
    fun getAllReservations(): LiveData<List<Reservation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Reservation>)

    @Transaction
    @Query("select * from reservations where `from`=:date")
    fun getReservations(date: Long): LiveData<List<Reservation>>

}