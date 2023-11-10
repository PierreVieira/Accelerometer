package com.example.testapplication.domain.usecase

import com.example.testapplication.domain.model.Coordinates
import com.example.testapplication.domain.repository.CoordinatesRepository

class SendCoordinatesUseCase(
    private val repository: CoordinatesRepository
) {
    suspend operator fun invoke(coordinates: Coordinates) {
        repository.sendCoordinates(coordinates)
    }
}
