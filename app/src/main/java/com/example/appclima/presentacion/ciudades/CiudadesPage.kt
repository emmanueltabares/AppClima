package com.example.appclima.presentacion.clima

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appclima.presentacion.ciudades.CiudadesView
import com.example.appclima.presentacion.ciudades.CiudadesViewModel
import com.example.appclima.presentacion.ciudades.CiudadesViewModelFactory
import com.example.appclima.repository.RepositorioApi

@Composable
fun CiudadesPage(
    modifier: Modifier = Modifier
) {
    val viewModel : CiudadesViewModel = viewModel(
        factory = CiudadesViewModelFactory(RepositorioApi())
    )
    CiudadesView(
        modifier = modifier,
        estado = viewModel.uiState
    ) { intencion ->
        viewModel.ejecutar(intencion)
    }

}