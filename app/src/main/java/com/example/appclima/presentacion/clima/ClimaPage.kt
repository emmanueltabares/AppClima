package com.example.appclima.presentacion.clima

import ClimaView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ClimaPage(
    modifier: Modifier = Modifier
) {
    val viewModel : ClimaViewModel = viewModel(
        factory = ClimaViewModel.factory
    )
    ClimaView(
        modifier = modifier,
        estado = viewModel.uiState
    ) { intencion ->
        viewModel.ejecutar(intencion)
    }



}