package br.senai.sp.saveeats.tips.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.tips.components.HeaderTips
import coil.compose.AsyncImage

@Composable
fun TipScreen(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current

    val imageTip = localStorage.readDataString(context,"imageTip")
    val nameTip = localStorage.readDataString(context,"nameTip")
    val descriptionTip = localStorage.readDataString(context,"descriptionTip")

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {

            HeaderTips(text = nameTip!!, navController = navController)

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface (
                    modifier = Modifier
                        .width(300.dp)
                        .height(300.dp)
                ) {

                    AsyncImage(
                        model = imageTip!!,
                        contentDescription = "Image Tips"
                    )

                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = descriptionTip!!
                )

            }

        }

    }

}