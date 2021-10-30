package com.example.hier.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.hier.models.LocationWithRooms

@Dao
interface LocationDao {

    @Transaction
    @Query("select * from location")
    fun getAllLocations(): LiveData<List<LocationWithRooms>>
}
