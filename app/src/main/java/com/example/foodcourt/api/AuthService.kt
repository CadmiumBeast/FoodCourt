package com.example.foodcourt.api
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("signup")
    fun signup(@Body userData: UserSignupData): Call<Void>

    @POST("login")
    fun login(@Body userData: UserLoginData): Call<TokenResponse>
}

data class UserSignupData(val username: String, val password: String)
data class UserLoginData(val username: String, val password: String)
data class TokenResponse(val token: String)