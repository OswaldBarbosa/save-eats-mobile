package br.senai.sp.saveeats.homecomponents.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.R

@Composable
fun HomeScreen(navController: NavHostController) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier
                        .size(60.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo"
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(25.dp),
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Location",
                        tint = Color(93, 182, 49)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Rua Elton Silva 95, Jandira",
                        fontSize = 17.sp

                    )

                }

                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    painter = painterResource(id = R.drawable.carrinho),
                    contentDescription = "Carrinho de Compras",
                    tint = Color(93, 182, 49)
                )

            }

            

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen() {
    HomeScreen(navController = rememberNavController())
}