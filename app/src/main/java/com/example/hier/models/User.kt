package com.example.hier.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(val username: String, val password: String) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}