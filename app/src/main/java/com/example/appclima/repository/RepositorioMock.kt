package com.example.appclima.repository

import com.example.appclima.repository.modelos.Ciudad
import com.example.appclima.repository.modelos.Clima

class RepositorioMock : Repositorio {
    override suspend fun buscarCiudad(ciudad: String): List<Ciudad> {

        val ciudad1 = Ciudad(
            name = "Buenos Aires ",
            lat = -20.0,
            lon = -25.4,
            country = "AR",
            state = "Argentina"
        )

        val ciudad2 = Ciudad(
            name = "Cordoba",
            lat = -20.0,
            lon = -25.4,
            country = "AR",
            state = "Argentina"
        )

        val ciudad3 = Ciudad(
            name = "La Plata",
            lat = -20.0,
            lon = -25.4,
            country = "AR",
            state = "Argentina"
        )

        return listOf(ciudad1, ciudad2, ciudad3)
    }

    override suspend fun obtenerClima(ciudad: Ciudad): Clima {
        TODO("Not yet implemented")
    }

    override suspend fun obtenerPronostico(ciudad: String): List<Clima> {
        TODO("Not yet implemented")
    }
}