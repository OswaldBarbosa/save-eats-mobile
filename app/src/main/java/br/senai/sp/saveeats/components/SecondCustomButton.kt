package br.senai.sp.saveeats.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.ui.theme.fontFamily

@Composable
fun SecondCustomButton(
    onClick: () -> Unit,
    text: String
) {

    Button(
        onClick,
        modifier = Modifier
            .width(135.dp)
            .height(45.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.green_save_eats_light)
        )
    ) {

        Text(
            text = text.uppercase(),
            fontSize = 15.sp,
            fontFamily = fontFamily,
            color = colorResource(id = R.color.white)
        )

    }

}