package br.senai.sp.saveeats.profilecomponents.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage

@Composable
fun ProfileScreen(localStorage: Storage) {

    val context = LocalContext.current
    val scroll = rememberScrollState()

    val nameClient = localStorage.readDataString(context, "nameClient")

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 35.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.foto),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )

                Text(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    text = nameClient!!,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )


            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    color = colorResource(id = R.color.white)
                ) {

                    Row (
                        modifier = Modifier
                            .padding(start = 18.dp, top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )  {

                        Icon(
                            modifier = Modifier
                                .size(28.dp),
                            painter = painterResource(id = R.drawable.profile), contentDescription = "Profile"
                        )

                        Column (
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {

                            Text(
                                text = "Meus dados",
                                fontSize = 15.sp
                            )

                            Text(
                                text = "Minhas informações de conta",
                                fontSize = 14.sp,
                                color = Color(123, 125, 123)
                            )

                        }

                    }

                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(.2.dp)
                        .padding(start = 15.dp),
                    color = Color.Black
                )

            }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun teste() {
}