package com.example.appgorjeta

import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.appgorjeta.ui.theme.AppGorjetaTheme
import com.example.slider.components.ComponenteValor
import org.intellij.lang.annotations.JdkConstants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGorjetaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var valorCampo by remember {
        mutableStateOf(0)
    }
    var valorGorjeta by remember {
        mutableStateOf(0)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {

        OutlinedTextField(
            //value = "${valor}",
            value = valorCampo.toString(),
            onValueChange = {
                if (it.length == 0) {
                    valorCampo = 0
                } else {
                    if (it.isDigitsOnly()) {
                        valorCampo = it.toInt()
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            label = { Text(text = "Valor da conta", fontWeight = FontWeight.Bold)}
        )
        Spacer(modifier = Modifier.padding(24.dp))
        Text(text = "Gorjeta personalizada: 18% a 30%", fontWeight = FontWeight.Bold)

        ComponenteValor(valorGorjeta, onValorChange = { valorGorjeta = it })

        Column() {

            Row() {
                Spacer(modifier = Modifier.padding(24.dp))
                Text(text = "15%")//Valor fixo da gorjeta
                Spacer(modifier = Modifier.padding(all = 8.dp))
                MostrarValorPersonalizado(valorGorjeta) //Valor da porcentagem
            }
            Spacer(modifier = Modifier.padding(all = 8.dp))

            Row() {
                Text(text = "Tip", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(all = 15.dp))
                MostrarValor15(valorCampo) //Valor da porcentagem fixa 15%
                Spacer(modifier = Modifier.padding(all = 8.dp))
                MostrarValorPerso(valorCampo.toDouble(), valorGorjeta.toDouble())


            }
            Spacer(modifier = Modifier.padding(all = 8.dp))

            Row() {
                Text(text = "Total", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(all = 8.dp))
                MostrarValorTotalFixo(valorCampo)
                Spacer(modifier = Modifier.padding(all = 8.dp))
                MostrarValorTotPerso(valorCampo.toDouble(), valorGorjeta.toDouble())
            }

        }

    }
}

@Composable
fun MostrarValor15(valor: Int) {
    Text(text = "${valor * 0.15}", style = MaterialTheme.typography.subtitle1)
}

@Composable
fun MostrarValorTotalFixo(valor: Int) {
    Text(text = "${valor + (valor * 0.15)}", style = MaterialTheme.typography.subtitle1)
}

@Composable
fun MostrarValorPersonalizado(valor: Int) {
    Text(text = "${valor}%", style = MaterialTheme.typography.subtitle1)
}

@Composable
fun MostrarValorPerso(valorCampo: Double, valorGorjeta: Double) {

    Text(text = "${(valorGorjeta / 100) * valorCampo}", style = MaterialTheme.typography.subtitle1)
}

@Composable
fun MostrarValorTotPerso(valorCampo: Double, valorGorjeta: Double) {
    var aux: Double = 0.0
    aux = (valorGorjeta / 100) * valorCampo

    Text(text = "${valorCampo + aux}", style = MaterialTheme.typography.subtitle1)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppGorjetaTheme {
        MyApp()
    }
}