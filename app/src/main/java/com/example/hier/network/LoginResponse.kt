package com.example.hier.network

import com.example.hier.models.User

data class LoginResponse(val error: Boolean, val message: String, val user: User)
