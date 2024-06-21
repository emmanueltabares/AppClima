package com.example.appclima.presentacion.ciudades

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appclima.presentacion.ciudades.CiudadesIntencion.Buscar
import com.example.appclima.presentacion.ciudades.CiudadesIntencion.Seleccionar
import com.example.appclima.repository.Repositorio
import com.example.appclima.repository.RepositorioMock
import com.example.appclima.repository.modelos.Ciudad
import com.example.appclima.router.Router
import com.example.appclima.router.Ruta
import kotlinx.coroutines.launch

class CiudadesViewModel(
    private val repositorio: Repositorio,
    private val router: Router
) : ViewModel() {

    var uiState by mutableStateOf<CiudadesEstado>(CiudadesEstado.Vacío)

    fun ejecutar(intencion: CiudadesIntencion) {

        when (intencion) {
            is Buscar -> buscarCiudad(nombre = intencion.nombre)
            is Seleccionar -> seleccionarCiudad(ciudad = intencion.ciudad)
        }
    }

    private fun buscarCiudad(nombre: String) {
        uiState = CiudadesEstado.Cargando
        viewModelScope.launch {
            try {
                val ciudades = repositorio.buscarCiudad((nombre))
                if (ciudades.isEmpty()) {
                    uiState = CiudadesEstado.Vacío
                } else {
                    uiState = CiudadesEstado.Correcto(ciudades)
                }
            } catch (exception: Exception) {
                println("Error: ${exception.cause}")
                uiState = CiudadesEstado.Error("Error al buscar ciudad")
            }
        }
    }

    private fun seleccionarCiudad(ciudad: Ciudad) {
        val ruta = Ruta.Clima(
            lat = ciudad.lat,
            lon = ciudad.lon,
            nombre = ciudad.name
        )

        router.navegar(ruta)
    }
}

class CiudadesViewModelFactory(
    private val repositorio: Repositorio,
    private val router: Router
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CiudadesViewModel::class.java)) {
            return CiudadesViewModel(repositorio, router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}