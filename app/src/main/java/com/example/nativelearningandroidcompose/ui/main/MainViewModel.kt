package com.example.nativelearningandroidcompose.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nativelearningandroidcompose.data.CoworkingSpace
import com.example.nativelearningandroidcompose.data.DataRepository
import com.example.nativelearningandroidcompose.utils.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.reflect.Type

class MainViewModel(private val repository: DataRepository, private val context: Context?): ViewModel() {
    private val jsonFile = "coworking-spaces.json"
    val responseData : MutableStateFlow<List<CoworkingSpace>> = MutableStateFlow(emptyList())
    fun getCoworkingSpaces() {
        viewModelScope.launch {
            try {
                makeApiCall()
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }

    fun getCoworkingSpaceByOrganisation(organisation: String): CoworkingSpace? {
        return responseData.value.find { it.organisation == organisation }
    }

    private fun makeApiCall() {
        repository.getAllCoworkSpaces().enqueue(object : retrofit2.Callback<List<CoworkingSpace>> {
            override fun onFailure(call: Call<List<CoworkingSpace>?>, t: Throwable) {
                responseData.value = fetchJson()
            }

            override fun onResponse(
                call: Call<List<CoworkingSpace>>,
                response: Response<List<CoworkingSpace>>
            ) {
                if (!response.isSuccessful) responseData.value = fetchJson()
                else responseData.value = response.body()!!
            }
        })
    }

    private fun fetchJson() : List<CoworkingSpace>
    {
        if (context == null)
            return emptyList()

        val jsonExtension = JsonUtil()
        val gson = Gson()
        val listType: Type = object : TypeToken<ArrayList<CoworkingSpace?>?>() {}.type
        return gson.fromJson(jsonExtension.loadJSONFromAsset(context, jsonFile), listType)
    }
}