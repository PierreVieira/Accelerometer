package com.example.testapplication.domain.repository

import com.example.testapplication.domain.model.Coordinates

interface CoordinatesRepository {
    suspend fun sendCoordinates(coordinates: Coordinates)
}
