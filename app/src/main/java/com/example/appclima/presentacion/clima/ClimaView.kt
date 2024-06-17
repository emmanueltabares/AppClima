import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.example.appclima.presentacion.clima.ClimaEstado
import com.example.appclima.presentacion.clima.ClimaIntencion
import com.example.appclima.ui.theme.AppClimaTheme

@Composable
fun ClimaView(
    modifier: Modifier = Modifier,
    estado: ClimaEstado,
    onAction: (ClimaIntencion) -> Unit
) {

    Column(modifier = modifier.padding(20.dp)) {

        when (estado) {
            is ClimaEstado.Error -> ErrorView(mensaje = estado.mensaje)
            is ClimaEstado.Exitoso -> ClimaView(
                ciudad = estado.ciudad,
                temperatura = estado.temperatura,
                descripcion = estado.descripcion,
                st = estado.st
            )
            ClimaEstado.Cargando -> LoadingView()
            ClimaEstado.Vacio -> EmptyView()
        }
        Spacer(modifier = Modifier.height(100.dp))
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

@Composable
fun ClimaView(ciudad: String, temperatura: Double, descripcion: String, st:Double){
    Column {
        Text(text = ciudad, style = MaterialTheme.typography.titleMedium)
        Text(text = "${temperatura}°", style = MaterialTheme.typography.titleLarge)
        Text(text = descripcion, style = MaterialTheme.typography.bodyMedium)
        Text(text = "sensacionTermica: ${st}°", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewVacio() {
    AppClimaTheme {
        ClimaView(estado = ClimaEstado.Vacio, onAction = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewError() {
    AppClimaTheme {
        ClimaView(estado = ClimaEstado.Error("Se rompio todo"), onAction = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewExitoso() {
    AppClimaTheme {
        ClimaView(estado = ClimaEstado.Exitoso(ciudad = "Mendoza", temperatura = 0.0), onAction = {})
    }
}