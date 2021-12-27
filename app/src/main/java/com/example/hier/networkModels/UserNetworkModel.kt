package com.example.hier.networkModels

import com.example.hier.models.User

data class UserNetworkModel(
    val customerId: Int,
    val firstname: String?,
    val lastname: String?,
    val username: String,
    val email: String?,
    val btw: String?,
    val tel: String?,
) {
    fun toDatabaseModel(): User {
        return User(username, customerId)
    }
}
