package com.example.hier.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class Room(val name: String) {
    @PrimaryKey(autoGenerate = true)
    var roomId: Int = 0;
}