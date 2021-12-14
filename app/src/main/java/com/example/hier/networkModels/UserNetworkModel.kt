package com.example.hier.networkModels

import com.example.hier.models.User

data class UserNetworkModel(
    val customerId: Int,
    val userName: String,
){
    fun toDatabaseModel() : User {
        val user = User(userName)
        user.userId = customerId
        return user
    }
}