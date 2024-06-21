package com.example.appclima.presentacion.ciudades

sealed class CiudadesIntencion {
    data class Buscar(val nombre: String): CiudadesIntencion()
    data class Seleccionar(val indice: String): CiudadesIntencion()
}