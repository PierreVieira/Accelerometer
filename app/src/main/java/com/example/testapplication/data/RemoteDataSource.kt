package com.example.testapplication.data

import com.example.testapplication.data.dto.CoordinatesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource(
    private val service: CoordinatesApi
) {
    suspend fun sendCoordinates(coordinates: CoordinatesDto){
        withContext(Dispatchers.IO) {
            service.sendCoordinates(coordinates)
        }
    }
}
