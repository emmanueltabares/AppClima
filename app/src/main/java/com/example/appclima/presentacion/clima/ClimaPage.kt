package com.example.appclima.presentacion.clima

import ClimaView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appclima.repository.RepositorioApi
import com.example.appclima.router.Enrutador

@Composable
fun ClimaPage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    lat : Float,
    lon : Float,
    nombre: String
) {
    val viewModel : ClimaViewModel = viewModel(
        factory = ClimaViewModelFactory(
            RepositorioApi(),
            router = Enrutador(navHostController),
            lat,
            lon,
            nombre
        )
    )
    ClimaView(
        modifier = modifier,
        estado = viewModel.uiState
    ) { intencion ->
        viewModel.ejecutar(intencion)
    }

}