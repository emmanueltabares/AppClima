package com.example.appclima

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appclima.presentacion.clima.CiudadesPage
import com.example.appclima.presentacion.clima.ClimaPage
import com.example.appclima.router.Enrutador
import com.example.appclima.router.Ruta

@Composable
fun MainPage(modifier : Modifier = Modifier) {
    val navHostController = rememberNavController()
    
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Ruta.Ciudades.id
    ) {
        composable(
            route = Ruta.Ciudades.id
        ) {
            CiudadesPage(modifier = Modifier, navHostController)
        }

        composable(
            route = Ruta.Clima().id
        ) {
            ClimaPage(modifier = Modifier, navHostController)
        }
    }
    
}