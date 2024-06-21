package com.example.appclima.repository

import com.example.appclima.repository.modelos.Ciudad
import com.example.appclima.repository.modelos.Clima
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RepositorioApi : Repositorio {

    private val apiKey = "98402b19b1832dc7559df28336f0e3"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    override suspend fun buscarCiudad(ciudad: String): List<Ciudad> {
        val res = client.get("htpp://api.openweathermap.org/geo/1.0/direct"){
            parameter("q", ciudad)
            parameter("limit", 5)
            parameter("appid", apiKey)
        }

        if(res.status == HttpStatusCode.OK) {
            val ciudades = res.body<List<Ciudad>>()
            return ciudades
        } else {
            throw Exception()
        }
    }

    override suspend fun obtenerClima(ciudad: Ciudad): Clima {
        TODO("Not yet implemented")
    }

    override suspend fun obtenerPronostico(ciudad: String): List<Clima> {
        TODO("Not yet implemented")
    }
}