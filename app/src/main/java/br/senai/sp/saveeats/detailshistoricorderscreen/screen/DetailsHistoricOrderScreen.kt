package br.senai.sp.saveeats.detailshistoricorderscreen.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.Header
import br.senai.sp.saveeats.model.OrderInformation
import br.senai.sp.saveeats.model.OrderList
import br.senai.sp.saveeats.model.ProductOrderList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.ui.theme.fontFamily
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import retrofit2.Call
import retrofit2.Response
import java.util.Locale

@Composable
fun DetalhesPedidoHistoricoScreen(
    navController: NavController,
    localStorage: Storage
) {

    val context = LocalContext.current

    val sumDeliveryProduct = 3.99 + 14.99

    val formattedSum = sumDeliveryProduct?.let {
        String.format("%.2f", it)
    }

    val waitingAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.waiting_animation))

    val verifierAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.verified_animation))

    val idOrder = localStorage.readDataInt(context, "idOrder")

    var productsOrder by remember {
        mutableStateOf(
            listOf<ProductOrderList>()
        )
    }

    var detailsOrder by remember {
        mutableStateOf(
            listOf<OrderInformation>()
        )
    }

    var status by remember {
        mutableStateOf(false)
    }

    val callOrder = RetrofitFactory
        .getOrderById()
        .getOrderById(idOrder)

    callOrder.enqueue(object : retrofit2.Callback<OrderList> {
        override fun onResponse(
            call: Call<OrderList>,
            response: Response<OrderList>
        ) {
            detailsOrder = listOf(response.body()!!.detalhes_do_pedido)
            productsOrder = response.body()!!.detalhes_do_pedido.produtos
            productsOrder = response.body()!!.detalhes_do_pedido.produtos
            status = true
        }

        override fun onFailure(
            call: Call<OrderList>,
            t: Throwable
        ) {
            Log.e("error", "onFailure: ${t.message}")
        }

    })

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(
                text = stringResource(id = R.string.order_details),
                navController = navController
            )

            if (status) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    AsyncImage(
                        model = localStorage.readDataString(context, "foto_restaurante"),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Column(
                        modifier = Modifier.absoluteOffset(x = (-15).dp)
                    ) {
                        Text(
                            text = detailsOrder[0].nome_restaurante,
                            fontSize = 20.sp,
                            color = Color(0, 0, 0),
                            fontWeight = FontWeight(500)
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Número do pedido:",
                                fontSize = 16.sp,
                                fontFamily = fontFamily
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = detailsOrder[0].numero_pedido,
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Black
                            )

                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = stringResource(id = R.string.see_menu),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(41, 95, 27),
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("products_restaurant_screen")
                                    localStorage.saveDataString(
                                        context,
                                        detailsOrder[0].nome_restaurante,
                                        "nameRestaurant"
                                    )

                                }

                        )
                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    colors = CardDefaults.cardColors(
                        Color(239, 239, 239)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            if (detailsOrder[0].status_pedido == "Pedido entregue") {
                                LottieAnimation(
                                    composition = verifierAnimation,
                                    modifier = Modifier.size(20.dp),
                                    iterations = LottieConstants.IterateForever
                                )


                            } else {
                                LottieAnimation(
                                    composition = waitingAnimation,
                                    modifier = Modifier.size(20.dp),
                                    iterations = LottieConstants.IterateForever
                                )

                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = detailsOrder[0].status_pedido,
                                fontSize = 17.sp
                            )

                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.5.dp),
                    colors = CardDefaults.cardColors(
                        Color(255, 141, 6)
                    )
                ) {}

                Spacer(modifier = Modifier.height(15.dp))

                LazyColumn {
                    items(productsOrder) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AsyncImage(
                                model = it.imagem_produto,
                                contentDescription = "Imagem do produto",
                                modifier = Modifier
                                    .size(65.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(
                                modifier = Modifier
                                    .width(250.dp),
                                horizontalAlignment = Alignment.Start
                            ) {

                                Text(
                                    text = it.nome_produto,
                                    fontSize = 18.sp
                                )

                                Text(
                                    text = it.descricao_produto,
                                    color = Color(104, 104, 104),
                                    fontSize = 12.sp
                                )

                            }

                            Text(text = "14.99")

                        }

                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Column {

                    Text(
                        text = stringResource(id = R.string.summary_of_values),
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        color = colorResource(id = R.color.green_save_eats_light)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "SubTotal",
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            color = colorResource(id = R.color.gray),
                        )

                        Text(
                            text = "R$ 14.99"
                        )

                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Taxa de entrega",
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            color = colorResource(id = R.color.gray),
                        )

                        Text(
                            text = "R$ 3.99",
                            fontSize = 16.sp,
                            fontFamily = fontFamily
                        )

                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Total",
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            color = colorResource(id = R.color.gray),
                        )

                        Text(
                            text = "R$ $formattedSum"
                        )


                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Pagamento",
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        color = colorResource(id = R.color.green_save_eats_light)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = detailsOrder[0].nome_forma_pagamento,
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            color = colorResource(id = R.color.gray),
                        )

                        Row {

                            Icon(
                                painter = painterResource(id = R.drawable.baseline_pix_24),
                                contentDescription = "",
                                tint = Color(46, 189, 174),
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = "Pix",
                                fontSize = 16.sp,
                                fontFamily = fontFamily
                            )
                        }

                    }

                }

            }

        }

    }


}