package com.example.testapplication.data

import com.example.testapplication.data.dto.CoordinatesDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CoordinatesApi {
    @POST("/")
    suspend fun sendCoordinates(
        @Body coordinatesDto: CoordinatesDto,
    ): Response<Unit>
}
