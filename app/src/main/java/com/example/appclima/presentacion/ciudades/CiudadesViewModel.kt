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
import kotlinx.coroutines.launch

class CiudadesViewModel(
    private val repositorio: Repositorio
) : ViewModel() {

    var uiState by mutableStateOf<CiudadesEstado>(CiudadesEstado.Vacío)

    fun ejecutar(intencion: CiudadesIntencion) {

        when (intencion) {
            is Buscar -> buscarCiudad(nombre = intencion.nombre)
            is Seleccionar -> seleccionarCiudad(indice = intencion.indice)
        }
    }

    private fun buscarCiudad(nombre: String) {
        uiState = CiudadesEstado.Cargando
        viewModelScope.launch {
            try {
                val ciudades = repositorio.buscarCiudad((nombre))
                uiState = CiudadesEstado.Correcto(ciudades)
            } catch (exception: Exception) {
                uiState = CiudadesEstado.Error("Error al buscar ciudad")
            }
        }
    }

    private fun seleccionarCiudad(indice: String) {
        uiState = CiudadesEstado.Vacío
    }
}

class CiudadesViewModelFactory(
    private val repositorio: Repositorio,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CiudadesViewModel::class.java)) {
            return CiudadesViewModel(repositorio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}