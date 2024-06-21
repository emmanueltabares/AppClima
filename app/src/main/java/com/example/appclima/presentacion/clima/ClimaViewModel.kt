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
    private val repositorio: Repositorio,
    router : Router
) : ViewModel() {

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

class ClimaViewModelFactory(
    private val repositorio: Repositorio,
    private val router: Router
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
            return ClimaViewModel(repositorio, router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}