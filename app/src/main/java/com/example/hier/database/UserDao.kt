package com.example.hier.database

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
    suspend fun getUser(username: String): User

    @Transaction
    @Query("select * from users order by userId desc limit 1")
    suspend fun getNewestUser(): User

    @Insert(onConflict = REPLACE)
    suspend fun insert(user: User)
}
