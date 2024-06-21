package com.example.appclima.router

class MockRouter: Router {
    override fun navegar(ruta: Ruta) {
        println("Navegar a : ${ruta.id}")
    }
}