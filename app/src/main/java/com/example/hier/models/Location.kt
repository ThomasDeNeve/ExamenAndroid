package com.example.hier.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Location")
data class Location(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="street")
    var street: String,
    @ColumnInfo(name="number")
    var number: Int,
    @ColumnInfo(name="postalCode")
    var postalCode: String,
    @ColumnInfo(name="place")
    var place: String
) {
}