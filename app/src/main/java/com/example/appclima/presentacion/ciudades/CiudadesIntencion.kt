package com.example.appclima.presentacion.ciudades

import com.example.appclima.repository.modelos.Ciudad

sealed class CiudadesIntencion {
    data class Buscar(val nombre: String): CiudadesIntencion()
    data class Seleccionar(val ciudad: Ciudad): CiudadesIntencion()
}