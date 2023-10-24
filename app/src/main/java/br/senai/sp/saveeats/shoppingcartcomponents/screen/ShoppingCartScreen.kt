package br.senai.sp.saveeats.shoppingcartcomponents.screen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.senai.sp.saveeats.Storage
import coil.compose.AsyncImage

@Composable
fun ShoppingCartScreen(localStorage: Storage) {

    val context = LocalContext.current

    val imageProduct = localStorage.readDataString(context,"imageProduct")

    Surface (
        modifier = Modifier
            .width(100.dp)
            .height(40.dp)
    ) {

        AsyncImage(
            model = imageProduct,
            contentDescription = "Image Product"
        )

    }

}