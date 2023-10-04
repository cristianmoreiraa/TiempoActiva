package com.example.tiempoactiva

import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.updateTransition
import androidx.lifecycle.lifecycleScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiempoactiva.ui.theme.TiempoActivaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Variables globales
var estado: Boolean by mutableStateOf(true)  // Variable para controlar el estado
var time: Int by mutableStateOf(0)           // Variable para el tiempo

// Clase principal de la aplicación
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura la interfaz de usuario
        setContent {
            TiempoActivaTheme {
                // Contenedor de la interfaz de usuario
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Llama al composable Greeting con el valor de 'time'
                    Greeting(time)
                }
            }
        }

        // Inicia una corrutina para ejecutar 'timer()'
        lifecycleScope.launch {
            timer()
        }
    }

    override fun onResume() {
        super.onResume()
        updateUI()
        time = 0
    }

    // Se llama cuando la actividad se pausa
    override fun onPause() {
        super.onPause()

        // Registra el tiempo actual en el registro (Log)
        Log.d("Estado", time.toString())
    }

    // Muestra un mensaje emergente (Toast) con el valor de 'time'
    fun updateUI() {
        Toast.makeText(this, "EL tiempo activo: $time", Toast.LENGTH_LONG).show()

    }
}

// Función suspendida que incrementa 'time' en 1 cada 100 ms mientras 'estado' sea verdadero
suspend fun timer() {
    while (estado) {
        delay(1000)
        time++
    }
}

// Composable que muestra un saludo con el valor de 'time'
@Composable
fun Greeting(time: Int, modifier: Modifier = Modifier) {
    Text(
        text = "El tiempo activo: $time!",
        style = TextStyle(fontSize = 48.sp) // Aumenta el tamaño del texto a 24sp
    )
}


// Previsualización de la interfaz de usuario
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TiempoActivaTheme {
        Greeting(time)
    }
}
