package br.senai.sp.saveeats.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CustomButton(
    navController: NavController,
    textButton: String
) {
    Button(
        modifier = Modifier
            .height(60.dp)
            .width(200.dp),
        onClick = {navController.navigate("home_screen")},
        colors = ButtonDefaults.buttonColors(Color(255,141,6))
    ) {
        Text(
            text = textButton,
            fontSize = 20.sp
        )
    }
}