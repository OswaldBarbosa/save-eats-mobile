package br.senai.sp.saveeats.trackorder.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.ui.theme.fontFamily
import coil.compose.AsyncImage
import com.google.firebase.firestore.local.LocalStore

@Composable
fun TrackOrder(navController: NavController, localStore: Storage) {

    val context = LocalContext.current

    val imageRestaurant = localStore.readDataString(context, "imageRestaurant")
    val nameRestaurant = localStore.readDataString(context, "nameRestaurant")

    val streetClient = localStore.readDataString(context, "streetClient")
    val numberAddressClient = localStore.readDataInt(context, "numberAddressClient")
    val cityClient = localStore.readDataString(context, "cityClient")
    val stateClient = localStore.readDataString(context, "stateClient")

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier.offset(x = 30.dp, y = 58.dp)
                ) {

                    IconButton(modifier = Modifier.size(20.dp), onClick = {
                        navController.popBackStack()
                    }) {

                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Arrow",
                            tint = Color(76, 132, 62)
                        )

                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "ACOMPANHAR PEDIDO",
                        fontSize = 18.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W400
                    )

                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier
                        .width(380.dp)
                        .height(240.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = Color(250, 250, 250)
                ) {

                    Row {

                        Column(
                            modifier = Modifier
                                .padding(start = 15.dp, top = 15.dp)
                        ) {

                            Row {

                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Icon Check",
                                    tint = colorResource(id = R.color.green_save_eats_light)
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                ) {

                                    Text(
                                        text = "Pedido realizado",
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                    Text(
                                        text = "18:40",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                            Divider(
                                modifier = Modifier
                                    .width(1.5.dp)
                                    .height(20.dp)
                                    .offset(x = 10.dp, y = -(5.dp)),
                                color = Color.Black
                            )

                            Row {

                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Icon Check",
                                    tint = colorResource(id = R.color.green_save_eats_light)
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                ) {

                                    Text(
                                        text = "Pedido realizado",
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                    Text(
                                        text = "18:40",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                            Divider(
                                modifier = Modifier
                                    .width(1.5.dp)
                                    .height(20.dp)
                                    .offset(x = 10.dp, y = -(5.dp)),
                                color = Color.Black
                            )

                            Row {

                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Icon Check",
                                    tint = colorResource(id = R.color.green_save_eats_light)
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                ) {

                                    Text(
                                        text = "Pedido realizado",
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                    Text(
                                        text = "18:40",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                            Divider(
                                modifier = Modifier
                                    .width(1.5.dp)
                                    .height(20.dp)
                                    .offset(x = 10.dp, y = -(5.dp)),
                                color = Color.Black
                            )

                            Row {

                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Icon Check",
                                    tint = colorResource(id = R.color.green_save_eats_light)
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                ) {

                                    Text(
                                        text = "Pedido realizado",
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                    Text(
                                        text = "18:40",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 15.dp, top = 15.dp),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {

                                Text(
                                    text = "Status do pedido",
                                    fontFamily = fontFamily
                                )

                                Text(
                                    modifier = Modifier
                                        .offset(x = 35.dp),
                                    text = "Despachado",
                                    fontFamily = fontFamily,
                                    color = colorResource(
                                        id =
                                        R.color.green_save_eats_light
                                    )
                                )

                            }

                            Column {

                                Text(
                                    text = "Previsão de entrega",
                                    fontFamily = fontFamily
                                )

                                Row(
                                    modifier = Modifier
                                        .padding(bottom = 15.dp)
                                        .offset(x = 30.dp)
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.AccessTime,
                                        contentDescription = "Icon Time",
                                        tint = colorResource(id = R.color.green_save_eats_light)
                                    )

                                    Text(
                                        text = "18:30",
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Black
                                    )

                                    Divider(
                                        modifier = Modifier
                                            .width(5.dp)
                                            .height(1.5.dp)
                                    )

                                    Text(
                                        text = "19:00",
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Black
                                    )

                                }

                            }

                        }

                    }

                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 25.dp)
            ) {

                Text(
                    text = "Detalhes do pedido",
                    fontSize = 22.sp,
                    fontFamily = fontFamily,
                    color = colorResource(id = R.color.green_save_eats_light)
                )

                Row {

                    AsyncImage(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = CircleShape),
                        model = imageRestaurant,
                        contentDescription = "Image Restaurant"
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {

                        Text(
                            text = nameRestaurant!!
                        )

                        Text(
                            text = "Ver cardápio",
                            fontFamily = fontFamily,
                            color = colorResource(id = R.color.green_save_eats_light)
                        )

                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(id = R.string.deliver_to),
                        fontSize = 18.sp,
                        fontFamily = fontFamily
                    )

                    Row {

                        Text(
                            text = "$streetClient,",
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(1.dp))

                        Text(
                            text = "$numberAddressClient -",
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                       Text(
                            text = "$cityClient -",
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = stateClient!!,
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.total),
                        fontSize = 18.sp,
                        fontFamily = fontFamily
                    )

                    Text(
                        text = "R$: 65,50",
                        fontWeight = FontWeight.Black,
                        fontFamily = fontFamily
                    )

                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "Pagamento",
                        fontFamily = fontFamily
                    )

                    Text(
                        text = "Débito Mastercard",
                        fontWeight = FontWeight.Black,
                        fontFamily = fontFamily
                    )

                }

            }

        }

    }

}