package com.example.testapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.testapplication.domain.model.Coordinates
import com.example.testapplication.presentation.CoordinatesViewModel
import com.example.testapplication.ui.theme.TestApplicationTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity(), SensorEventListener {

    private val viewModel: CoordinatesViewModel by viewModels {
        CoordinatesViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                // A surface container using the 'background' color from the theme
                val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
                val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                sensorManager.registerListener(
                    this,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL
                )
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        val textValue by viewModel.buttonText.collectAsState()
                        Button(
                            onClick = viewModel::toggle
                        ) {
                            Text(text = textValue)
                        }
                    }
                }
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.run {
            viewModel.sendCoordinates(
                coordinates = Coordinates(
                    x = values[0].toString(),
                    y = values[1].toString(),
                    z = values[2].toString()
                )
            )
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}
