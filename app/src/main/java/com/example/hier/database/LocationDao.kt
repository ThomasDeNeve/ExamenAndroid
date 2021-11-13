package com.example.hier.database;

import androidx.lifecycle.LiveData;
import androidx.room.*
import com.example.hier.models.Location
import com.example.hier.models.LocationWithRooms
import com.example.hier.models.Reservation

@Dao
interface LocationDao {

    @Transaction
    @Query("select * from location")
    fun getAllLocations(): LiveData<List<LocationWithRooms>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Location>)

}
