package com.example.slider.components

import androidx.compose.material.Slider
import androidx.compose.runtime.Composable

@Composable
fun ComponenteValor(valor: Int, onValorChange: (Int) -> Unit) {
    Slider(
        value = valor.toFloat(),
        onValueChange = { onValorChange( it.toInt() ) },
        valueRange = 18f..30f,
        steps = 5
    )

}