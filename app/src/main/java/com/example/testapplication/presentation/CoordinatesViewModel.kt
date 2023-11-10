package com.example.testapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.testapplication.data.RemoteDataSource
import com.example.testapplication.data.repository.CoordinatesRepositoryImpl
import com.example.testapplication.di.ServiceBuilder
import com.example.testapplication.domain.model.Coordinates
import com.example.testapplication.domain.repository.CoordinatesRepository
import com.example.testapplication.domain.usecase.SendCoordinatesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoordinatesViewModel(
    private val sendCoordinatesUseCase: SendCoordinatesUseCase
): ViewModel() {

    private var canSendRequest: Boolean = false

    private val _buttonText: MutableStateFlow<String> = MutableStateFlow(START_MESSAGE)
    val buttonText: StateFlow<String> = _buttonText

    fun sendCoordinates(
        coordinates: Coordinates
    ) {
        if (canSendRequest) {
            Log.d("CoordinatesViewModel", "sendCoordinates: $coordinates")
            viewModelScope.launch {
                sendCoordinatesUseCase(coordinates)
            }
        }
    }

    fun toggle() {
        canSendRequest = !canSendRequest
        _buttonText.update {
            if (canSendRequest) {
                START_MESSAGE
            } else {
                STOP_MESSAGE
            }
        }
    }

    companion object {
        private const val START_MESSAGE = "start"
        private const val STOP_MESSAGE = "stop"
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T = CoordinatesViewModel(
                sendCoordinatesUseCase = SendCoordinatesUseCase(
                    repository = CoordinatesRepositoryImpl(
                        remoteDataSource = RemoteDataSource(
                            service = ServiceBuilder.service
                        )
                    )
                )
            ) as T
        }
    }
}
