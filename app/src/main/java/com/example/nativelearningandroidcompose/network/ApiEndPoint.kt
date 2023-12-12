package com.example.nativelearningandroidcompose.network

import com.example.nativelearningandroidcompose.data.CoworkingSpace
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {
    @GET("coworkspaces")
    fun getAllCoworkSpaces() : Call<List<CoworkingSpace>>
}