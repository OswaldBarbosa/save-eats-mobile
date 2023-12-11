package br.senai.sp.saveeats.profilerestaurant

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.Header
import br.senai.sp.saveeats.model.AddressRestaurant
import br.senai.sp.saveeats.model.AddressRestaurantList
import br.senai.sp.saveeats.model.EvaluationRestaurant
import br.senai.sp.saveeats.model.EvaluationRestaurantList
import br.senai.sp.saveeats.model.FormPayment
import br.senai.sp.saveeats.model.FormPaymentList
import br.senai.sp.saveeats.model.RestaurantOpeningHours
import br.senai.sp.saveeats.model.RestaurantOpeningHoursList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.ui.theme.fontFamily
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ProfileRestaurantScreen(
    navController: NavController, localStorage: Storage
) {

    val context = LocalContext.current

    val nameRestaurant = localStorage.readDataString(context, "nameRestaurant")
    val imageRestaurant = localStorage.readDataString(context, "imageRestaurant")
    val nameCategoryRestaurant = localStorage.readDataString(context, "nameCategoryRestaurant")
    val idRestaurant = localStorage.readDataInt(context, "idRestaurant")

    var progressState by remember {
        mutableStateOf(true)
    }

    var addressRestaurant by remember {
        mutableStateOf(
            listOf<AddressRestaurant>()
        )
    }

    var listEvaluationRestaurant by remember {
        mutableStateOf(
            listOf<EvaluationRestaurant>()
        )
    }

    var listRestaurantOpeningHours by remember {
        mutableStateOf(
            listOf<RestaurantOpeningHours>()
        )
    }

    var numberOfEvaluations by remember {
        mutableIntStateOf(0)
    }

    var mediaRestaurant by remember {
        mutableStateOf("0")
    }

    val callEvaluationRestaurant =
        RetrofitFactory.getEvaluationByIdRestaurant().getEvaluationRestaurant(idRestaurant)

    callEvaluationRestaurant.enqueue(object : Callback<EvaluationRestaurantList> {

        override fun onResponse(
            call: Call<EvaluationRestaurantList>, response: Response<EvaluationRestaurantList>
        ) {

            mediaRestaurant = response.body()!!.media_estrelas
            numberOfEvaluations = response.body()!!.quantidade_avaliacoes
            listEvaluationRestaurant = response.body()!!.avaliacoes_do_restaurante

        }

        override fun onFailure(
            call: Call<EvaluationRestaurantList>, t: Throwable
        ) {

            Log.e("error", "onFailure: ${t.message}")

        }


    })

    val callRestaurantAddress =
        RetrofitFactory.getAddressByIdRestaurant().getAddressRestaurantByID(idRestaurant)

    callRestaurantAddress.enqueue(object : Callback<AddressRestaurantList> {

        override fun onResponse(
            call: Call<AddressRestaurantList>, response: Response<AddressRestaurantList>
        ) {
            addressRestaurant = response.body()!!.endereco_restaurante
        }

        override fun onFailure(
            call: Call<AddressRestaurantList>, t: Throwable
        ) {
            Log.e("error", "onFailure: ${t.message}")
        }


    })

    val callRestaurantOpeningHours =
        RetrofitFactory.getOpeningHoursByIdRestaurant().getOpeningHoursByIdRestaurant(idRestaurant)

    callRestaurantOpeningHours.enqueue(object : Callback<RestaurantOpeningHoursList> {
        override fun onResponse(
            call: Call<RestaurantOpeningHoursList>, response: Response<RestaurantOpeningHoursList>
        ) {
            listRestaurantOpeningHours = response.body()!!.dias_horarios_funcionamento
        }

        override fun onFailure(
            call: Call<RestaurantOpeningHoursList>, t: Throwable
        ) {
            Log.e("ERROR", "${t.message}")
        }
    })

    var listFormPayment by remember {
        mutableStateOf<List<FormPayment>>(emptyList())
    }

    val callFormPayment = RetrofitFactory.getFormPayment().getFormPayment(idRestaurant)

    callFormPayment.enqueue(object : Callback<FormPaymentList> {
        override fun onResponse(
            call: Call<FormPaymentList>, response: Response<FormPaymentList>
        ) {
            listFormPayment = response.body()!!.formas_de_pagamento_do_restaurante
        }

        override fun onFailure(
            call: Call<FormPaymentList>, t: Throwable
        ) {

            Log.i("ds3t", "onFailure: ${t.message}")

        }

    })

    Surface(
        modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Header(
                text = stringResource(id = R.string.store_profile), navController = navController
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = nameRestaurant!!,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = fontFamily
                    )

                    Text(
                        text = nameCategoryRestaurant!!, fontSize = 16.sp, fontFamily = fontFamily
                    )

                }

                AsyncImage(
                    modifier = Modifier
                        .size(70.dp)
                        .offset(x = -(25).dp)
                        .clip(CircleShape),
                    model = imageRestaurant,
                    contentDescription = "Image Restaurant",
                    contentScale = ContentScale.Crop
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = mediaRestaurant,
                        color = Color(255, 200, 0, 255),
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "",
                        tint = if (mediaRestaurant.replace(",", ".").toDouble() >= 1.0) {
                            Color(255, 200, 0, 255)
                        } else {
                            Color(117, 117, 117, 255)
                        }
                    )

                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "",
                        tint = if (mediaRestaurant.replace(",", ".").toDouble() >= 2.0) {
                            Color(255, 200, 0, 255)
                        } else {
                            Color(117, 117, 117, 255)
                        }
                    )

                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "", tint =

                        if (mediaRestaurant.replace(",", ".").toDouble() >= 3.0) {
                            Color(255, 200, 0, 255)
                        } else {
                            Color(117, 117, 117, 255)
                        }

                    )

                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "",
                        tint = if (mediaRestaurant.replace(",", ".").toDouble() >= 4.0) {
                            Color(255, 200, 0, 255)
                        } else {
                            Color(117, 117, 117, 255)
                        }
                    )

                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "",
                        tint = if (mediaRestaurant.replace(",", ".").toDouble() >= 5.0) {
                            Color(255, 200, 0, 255)
                        } else {
                            Color(117, 117, 117, 255)
                        }
                    )

                }

                Row {

                    Text(
                        text = numberOfEvaluations.toString(),
                        fontSize = 14.sp,
                        fontFamily = fontFamily
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = stringResource(id = R.string.evaluations),
                        fontSize = 14.sp,
                        fontFamily = fontFamily
                    )

                }

            }

            Spacer(
                modifier = Modifier.height(25.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        modifier = Modifier
                            .padding(start = 55.dp)
                            .clickable { progressState = true },
                        text = stringResource(id = R.string.evaluations).uppercase(),
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Black,
                        color = Color.Black,
                    )

                    Text(
                        modifier = Modifier
                            .padding(end = 50.dp)
                            .clickable { progressState = false },
                        text = stringResource(id = R.string.information).uppercase(),
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(
                            color = if (progressState) {
                                Color(218, 218, 218)
                            } else {
                                Color(41, 97, 27)
                            }, shape = RoundedCornerShape(size = 5.dp)
                        )
                ) {

                    Row(
                        modifier = Modifier
                            .width(210.dp)
                            .height(2.dp)
                            .background(
                                color = if (!progressState) {
                                    Color(218, 218, 218)
                                } else {
                                    Color(41, 97, 27)
                                }, shape = RoundedCornerShape(size = 5.dp)
                            )
                    ) {}

                }

            }

            if (progressState) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    if (numberOfEvaluations == 0) {

                        Text(
                            text = stringResource(id = R.string.no_evaluation),
                            fontSize = 14.sp,
                            fontFamily = fontFamily
                        )

                    } else {

                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            items(listEvaluationRestaurant) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .height(100.dp)
                                        .padding(start = 15.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    if (it.foto_cliente == "") {

                                        Image(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "Profile Photo",
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop

                                        )

                                    } else {

                                        AsyncImage(
                                            model = it.foto_cliente,
                                            contentDescription = "Profile Photo",
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )

                                    }

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 15.dp)
                                    ) {

                                        Text(
                                            text = it.nome_cliente,
                                            fontWeight = FontWeight.Black,
                                            fontSize = 16.sp,
                                            fontFamily = fontFamily
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {

                                            Text(
                                                text = "${it.quantidade_estrela}",
                                                color = Color(255, 215, 0, 255),
                                                fontWeight = FontWeight(500),
                                                fontSize = 15.sp,
                                                fontFamily = fontFamily
                                            )

                                            Spacer(modifier = Modifier.width(5.dp))

                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 1) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier.size(20.dp)
                                            )

                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 2) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier.size(20.dp)
                                            )

                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 3) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier.size(20.dp)
                                            )

                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 4) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier.size(20.dp)
                                            )

                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "",
                                                tint = if (it.quantidade_estrela >= 5) {
                                                    Color(255, 215, 0, 255)
                                                } else {
                                                    Color(117, 117, 117, 255)
                                                },
                                                modifier = Modifier.size(20.dp)
                                            )

                                        }

                                        Spacer(modifier = Modifier.width(5.dp))

                                        Text(
                                            text = it.avaliacao_descricao,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Light,
                                            fontFamily = fontFamily,
                                            lineHeight = 14.sp
                                        )

                                    }

                                }

                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                )

                            }

                        }

                    }

                }

            } else {

                Spacer(modifier = Modifier.height(15.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 15.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.Place,
                            contentDescription = "Icon Place",
                            tint = colorResource(id = R.color.green_save_eats_light),
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = stringResource(id = R.string.address),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = fontFamily,
                            color = Color.Black
                        )

                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Column {

                        Text(
                            text = "${addressRestaurant[0].rua},${addressRestaurant[0].numero}",
                            fontSize = 16.sp,
                            fontFamily = fontFamily
                        )

                        Text(
                            text = "CEP - ${addressRestaurant[0].cep}",
                            fontSize = 16.sp,
                            fontFamily = fontFamily
                        )

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        )

                        {

                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.WatchLater,
                                contentDescription = "",
                                tint = colorResource(id = R.color.green_save_eats_light)
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = stringResource(id = R.string.opening_hours),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = fontFamily,
                                color = Color.Black
                            )

                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            items(listRestaurantOpeningHours) {

                                Spacer(modifier = Modifier.height(5.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Text(
                                        text = it.dia_semana,
                                        fontSize = 16.sp,
                                        fontFamily = fontFamily
                                    )

                                    Text(
                                        modifier = Modifier
                                            .offset(x = -(20).dp),
                                        text = "${it.horario_inicio} as ${it.horario_final}",
                                        fontSize = 16.sp,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                        }

                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.MonetizationOn,
                            contentDescription = "Icon Money",
                            tint = colorResource(id = R.color.green_save_eats_light),
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = stringResource(id = R.string.payment_methods),
                            fontWeight = FontWeight.Black,
                            fontSize = 20.sp,
                            fontFamily = fontFamily
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        Text(
                            text = stringResource(id = R.string.payment_methods_accepted),
                            fontSize = 16.sp,
                            fontFamily = fontFamily
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        
                        LazyColumn {

                            items(listFormPayment) {

                                Row {

                                    AsyncImage(
                                        modifier = Modifier
                                            .size(30.dp),
                                        model = it.foto_bandeira,
                                        contentDescription = "Image Payment"
                                    )

                                    Spacer(modifier = Modifier.width(5.dp))

                                    Text(
                                        text = it.nome_forma_pagamento,
                                        fontSize = 14.sp,
                                        fontFamily = fontFamily
                                    )

                                }

                            }

                        }

                    }

                }

            }

        }

    }

}