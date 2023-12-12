package com.example.nativelearningandroidcompose.data

import com.example.nativelearningandroidcompose.network.ApiEndPoint
import com.example.nativelearningandroidcompose.network.RetrofitClient

class DataRepository {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(ApiEndPoint::class.java)

    fun getAllCoworkSpaces() = retrofit.getAllCoworkSpaces()
}