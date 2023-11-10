package com.example.testapplication.di

import com.example.testapplication.data.CoordinatesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private const val URL = "https://enx92fd3fm5be.x.pipedream.net/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: CoordinatesApi = retrofit.create(CoordinatesApi::class.java)
}
