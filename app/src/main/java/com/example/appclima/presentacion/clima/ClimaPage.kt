package com.example.appclima.presentacion.clima

import ClimaView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appclima.presentacion.ciudades.CiudadesViewModel
import com.example.appclima.presentacion.ciudades.CiudadesViewModelFactory
import com.example.appclima.repository.RepositorioApi
import com.example.appclima.router.Enrutador
import com.example.appclima.router.Router

@Composable
fun ClimaPage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val viewModel : ClimaViewModel = viewModel(
        factory = ClimaViewModelFactory(
            RepositorioApi(),
            router = Enrutador(navHostController)
        )
    )
    ClimaView(
        modifier = modifier,
        estado = viewModel.uiState
    ) { intencion ->
        viewModel.ejecutar(intencion)
    }

}