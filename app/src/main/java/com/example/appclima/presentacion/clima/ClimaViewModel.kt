package com.example.appclima.presentacion.clima

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appclima.repository.Repositorio
import com.example.appclima.repository.RepositorioMock
import com.example.appclima.repository.modelos.Ciudad
import kotlinx.coroutines.launch

data class MainUIState (
    val ciudad: String = "",
    val temperatura: Int = 0,
    val descripcion: String = "",
    val st: Int = 0,
    val sinDatos: Boolean = true,
)

class ClimaViewModel(
    val repositorio: Repositorio
) : ViewModel() {

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorio = RepositorioMock()
                ClimaViewModel(repositorio)
            }
        }
    }

    var uiState by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    fun ejecutar(intencion: ClimaIntencion) {

        when(intencion) {
            ClimaIntencion.actualizarClima -> obtenerClima()
        }

    }

    private fun obtenerClima() {
        uiState = ClimaEstado.Cargando
        viewModelScope.launch {
            val cordoba = Ciudad(name = "Cordoba", lat = -31.4135, lon = -64.18105, country = "Ar")
            try{
                val clima = repositorio.obtenerClima(cordoba)
                uiState = ClimaEstado.Exitoso(
                    ciudad = clima.name ,
                    temperatura = clima.main.temp,
                    descripcion = clima.weather.first().description,
                    st = clima.main.feelsLike,
                )
            } catch (exception: Exception){
                uiState = ClimaEstado.Error(exception.localizedMessage ?: "error desconocido")
            }
        }
    }
}