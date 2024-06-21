package com.example.appclima.repository

import com.example.appclima.repository.modelos.Ciudad
import com.example.appclima.repository.modelos.Clima

interface Repositorio {
    suspend fun buscarCiudad(ciudad: String): List<Ciudad>
    suspend fun obtenerClima(lat : Float, lon : Float): Clima
    suspend fun obtenerPronostico(ciudad: String): List<Clima>
}