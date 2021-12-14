package com.example.hier.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.hier.models.User

@Dao
interface UserDao {

    @Transaction
    @Query("select * from users where username=:username")
    fun getUser(username: String): LiveData<User>

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

}