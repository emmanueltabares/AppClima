package com.example.appclima.presentacion.ciudades

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.appclima.repository.modelos.Ciudad

@Composable
fun CiudadesView (
    modifier: Modifier = Modifier,
    estado: CiudadesEstado,
    onAction: (CiudadesIntencion) -> Unit
) {

    var value by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(
            value = value,
            label = { Text(text = "Buscar por nombre") },
            onValueChange = {
                value = it
                onAction(CiudadesIntencion.Buscar(value))
            }
        );

        when (estado) {
            CiudadesEstado.Cargando -> LoadingView()
            is CiudadesEstado.Correcto -> ListaDeCiudades(
                estado.ciudades
            ) {
                onAction(CiudadesIntencion.Seleccionar(it))
            }
            is CiudadesEstado.Error -> ErrorView(mensaje = estado.mensaje)
            CiudadesEstado.Vacío -> EmptyView()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeCiudades(ciudades: List<Ciudad>, onSelect: (Ciudad) -> Unit ) {
    LazyColumn {
        items(items = ciudades) {
            Card(onClick = { onSelect(it) }) {
                Text(text = it.name)
            }
        }
    }
}

@Composable
fun EmptyView(){
    Text(text = "No hay nada que mostrar")
}

@Composable
fun LoadingView(){
    Text(text = "Cargando")
}

@Composable
fun ErrorView(mensaje: String){
    Text(text = mensaje)
}