package br.senai.sp.saveeats.presentationcomponents.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.components.CustomButton

@Composable
fun FirstPresentationScreen(navController: NavController) {

    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
         ) {

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                ) {

                Image(
                    painter = painterResource(id = R.drawable.chef),
                    contentDescription = "Chef",
                    modifier = Modifier
                        .size(410.dp)
                )

            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Normal
                )

                Text(
                    modifier = Modifier
                        .width(385.dp),
                    text = "Participe deste movimento sustentável que combate o desperdício de alimentos ao resgatar produtos frescos de restaurantes, padarias e hortifrutis com descontos de até 70%",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(98,98,98)
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){

                    Card (
                        Modifier
                            .width(22.dp)
                            .height(12.dp),
                        backgroundColor = Color(236,174,117)
                    ){}

                    Spacer(modifier = Modifier.width(35.dp))

                    Card (
                        Modifier
                            .width(22.dp)
                            .height(12.dp),
                        backgroundColor = Color(217,217,217)
                    ){}

                    Spacer(modifier = Modifier.width(35.dp))

                    Card (
                        Modifier
                            .width(22.dp)
                            .height(12.dp),
                        backgroundColor = Color(217,217,217)
                    ){}

                }

                CustomButton(
                    onCLick = { navController.navigate("second_presentation_screen") },
                    text = stringResource(id = R.string.next)
                )

            }


        }

    }

}