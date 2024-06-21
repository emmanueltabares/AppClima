package com.example.appclima.presentacion.clima

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.appclima.presentacion.ciudades.CiudadesViewModel
import com.example.appclima.repository.Repositorio
import com.example.appclima.repository.RepositorioMock
import com.example.appclima.repository.modelos.Ciudad
import com.example.appclima.router.Router
import kotlinx.coroutines.launch

class ClimaViewModel(
    val repositorio: Repositorio,
    val router : Router,
    val lat : Float,
    val lon : Float,
    val nombre: String
) : ViewModel() {

    var uiState by mutableStateOf<ClimaEstado>(ClimaEstado.Cargando)

    fun ejecutar(intencion: ClimaIntencion) {

        when(intencion) {
            ClimaIntencion.actualizarClima -> obtenerClima()
        }
    }

    fun obtenerClima() {
        viewModelScope.launch {
            try{
                val clima = repositorio.obtenerClima(lat = lat, lon = lon)
                uiState = ClimaEstado.Exitoso(
                    ciudad = nombre ,
                    temperatura = clima.main.temp,
                    descripcion = clima.weather.first().description,
                    st = clima.main.feels_like,
                )
            } catch (exception: Exception){
                uiState = ClimaEstado.Error(exception.localizedMessage ?: "error desconocido")
            }
        }
    }
}

class ClimaViewModelFactory(
    private val repositorio: Repositorio,
    private val router: Router,
    private val lat : Float,
    private val lon : Float,
    private val nombre: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
            return ClimaViewModel(
                repositorio,
                router,
                lat,
                lon,
                nombre
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}