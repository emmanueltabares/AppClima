package com.example.appclima

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appclima.presentacion.clima.CiudadesPage
import com.example.appclima.presentacion.clima.ClimaPage
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
            route = "clima?lat={lat}&lon={lon}&nombre={nombre}",
            arguments =  listOf(
                navArgument("lat") { type= NavType.FloatType },
                navArgument("lon") { type= NavType.FloatType },
                navArgument("nombre") { type= NavType.StringType }
            )
        ) {
            val lat = it.arguments?.getFloat("lat") ?: 0.0f
            val lon = it.arguments?.getFloat("lon") ?: 0.0f
            val nombre = it.arguments?.getString("nombre") ?: ""
            ClimaPage(modifier = modifier, navHostController, lat = lat, lon = lon, nombre = nombre)
        }
    }
    
}