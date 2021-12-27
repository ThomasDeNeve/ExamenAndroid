package com.example.hier.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.hier.models.Location
import com.example.hier.models.LocationWithRooms

@Dao
interface LocationDao {

    @Transaction
    @Query("select * from location")
    fun getAllLocations(): LiveData<List<LocationWithRooms>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Location>)

    @Query("select id from location where name=:name")
    fun getLocationIdByName(name: String): Int
}
