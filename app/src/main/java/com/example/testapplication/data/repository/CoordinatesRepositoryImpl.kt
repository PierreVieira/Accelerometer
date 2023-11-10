package com.example.testapplication.data.repository

import com.example.testapplication.data.RemoteDataSource
import com.example.testapplication.data.dto.CoordinatesDto
import com.example.testapplication.domain.model.Coordinates
import com.example.testapplication.domain.repository.CoordinatesRepository

class CoordinatesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): CoordinatesRepository {
    override suspend fun sendCoordinates(coordinates: Coordinates) {
        remoteDataSource.sendCoordinates(coordinates.toDto())
    }

    private fun Coordinates.toDto(): CoordinatesDto = CoordinatesDto(
        x = x,
        y = y,
        z = z
    )
}
