package com.example.hier.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(
    @PrimaryKey var username: String,
    @ColumnInfo(name = "userID") var userId: Int
)
