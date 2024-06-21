package com.example.appclima.presentacion.ciudades

import com.example.appclima.repository.modelos.Ciudad

sealed class CiudadesEstado {
    data object Vacío: CiudadesEstado()
    data object Cargando: CiudadesEstado()
    data class Correcto(val ciudades: List<String>) : CiudadesEstado()

    data class Error(val mensaje: String) : CiudadesEstado()

}