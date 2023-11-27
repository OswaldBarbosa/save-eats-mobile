package br.senai.sp.saveeats.order.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.Header
import br.senai.sp.saveeats.model.ClientAddress
import br.senai.sp.saveeats.model.ClientAddressList
import br.senai.sp.saveeats.model.DeliveryAreaRestaurant
import br.senai.sp.saveeats.model.DeliveryAreaRestaurantList
import br.senai.sp.saveeats.model.FormPayment
import br.senai.sp.saveeats.model.FormPaymentList
import br.senai.sp.saveeats.model.OrderRepository
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.order.components.SummaryOfValues
import br.senai.sp.saveeats.order.components.Title
import br.senai.sp.saveeats.ui.theme.fontFamily
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun OrderScreen(
    navController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope,
    teste: String,
    onValueChange: (String) -> Unit
) {

    val context = LocalContext.current

    var teste = teste

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val imageRestaurant = localStorage.readDataString(context, "imageRestaurant")
    val nameRestaurant = localStorage.readDataString(context, "nameRestaurant")

    val idProduct = localStorage.readDataInt(context, "idProductOne")
    idProduct.toString()
    var priceProduct = localStorage.readDataString(context, "priceProduct")

    priceProduct = priceProduct!!.replace(",", ".")

    priceProduct.toFloat()

    val deliveryValue = localStorage.readDataFloat(context, "deliveryValue")

    val sumDeliveryProduct = deliveryValue + priceProduct.toFloat()

    val formattedSum = sumDeliveryProduct.let {
        String.format("%.2f", it)
    }

    var addressClient by remember {
        mutableStateOf(listOf(ClientAddress()))
    }

    val callAddressClient = RetrofitFactory
        .getAddressClient()
        .getAddressClient(58)

    callAddressClient.enqueue(object : Callback<ClientAddressList> {
        override fun onResponse(
            call: Call<ClientAddressList>,
            response: Response<ClientAddressList>
        ) {
            addressClient = response.body()!!.endereco_cliente
        }

        override fun onFailure(
            call: Call<ClientAddressList>,
            t: Throwable
        ) {

            Log.e("ERROR", "onFailure: ${t.message}")

        }

    })

    var timeDelivery by remember {
        mutableStateOf(DeliveryAreaRestaurant())
    }

    val callTimeDelivery = RetrofitFactory
        .getDeliveryArea()
        .getDeliveryArea(1)

    callTimeDelivery.enqueue(object : Callback<DeliveryAreaRestaurantList> {
        override fun onResponse(
            call: Call<DeliveryAreaRestaurantList>,
            response: Response<DeliveryAreaRestaurantList>
        ) {
            timeDelivery = response.body()!!.frete_area_entrega_do_restaurante
        }

        override fun onFailure(
            call: Call<DeliveryAreaRestaurantList>,
            t: Throwable
        ) {

            Log.e("ERROR", "onFailure: ${t.message}")

        }

    })

    val deliveryTime = localStorage.readDataString(context, "deliveryTime")

    val idClient = localStorage.readDataInt(context, "idClient")
    val idRestaurant = localStorage.readDataInt(context, "idRestaurant")
    val cpfClient = localStorage.readDataString(context, "cpfClient")

    var listFormPayment by remember {
        mutableStateOf<List<FormPayment>>(emptyList())
    }

    val callFormPayment = RetrofitFactory.getFormPayment().getFormPayment(1)

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

    fun order(
        idStatus: Int,
        idRestaurantFormPayment: Int,
        idRestaurantDeliveryArea: Int,
        idClient: Int,
        idRestaurant: Int,
        idProducts: String
    ) {

        val order = OrderRepository()

        lifecycleScope.launch {

            val response = order.order(
                idStatus,
                idRestaurantFormPayment,
                idRestaurantDeliveryArea,
                idClient,
                idRestaurant,
                idProducts
            )

            if (response.isSuccessful) {

                navController.navigate("waiting_for_order_screen")

            } else {
                Toast.makeText(context, "Deu Erro", Toast.LENGTH_SHORT).show()
            }

        }

    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(
                text = stringResource(id = R.string.shopping_cart),
                navController = navController
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 30.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Surface(
                        modifier = Modifier
                            .size(50.dp),
                        shape = CircleShape,
                        elevation = 5.dp
                    ) {

                        AsyncImage(
                            model = imageRestaurant,
                            contentDescription = "Image Restaurant",
                            contentScale = ContentScale.FillHeight
                        )

                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = nameRestaurant!!,
                        fontSize = 16.sp,
                        fontFamily = fontFamily
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Title(text = stringResource(id = R.string.delivery_address))

                    Row {

                        Text(
                            text = "${addressClient[0].logradouro_cliente!!},",
                            fontSize = 15.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "${addressClient[0].numero_endereco_cliente} - ",
                            fontSize = 15.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "${addressClient[0].localidade_cliente} - ",
                            fontSize = 15.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = addressClient[0].uf_cliente.toString(),
                            fontSize = 15.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                    }

                    Text(
                        text = stringResource(id = R.string.standard_delivery),
                        fontSize = 14.5.sp,
                        fontFamily = fontFamily
                    )

                    Row {

                        Text(
                            text = stringResource(id = R.string.today),
                            fontSize = 14.sp,
                            fontFamily = fontFamily
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = timeDelivery.tempo_entrega.toString().replace("00:", ""),
                            fontSize = 14.sp,
                            fontFamily = fontFamily
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "min",
                            fontSize = 14.sp,
                            fontFamily = fontFamily
                        )

                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Title(text = stringResource(id = R.string.summary_of_values))

                    SummaryOfValues(
                        textOne = stringResource(id = R.string.subtotal),
                        textTwo = priceProduct!!
                    )

                    SummaryOfValues(
                        textOne = stringResource(id = R.string.delivery_fee),
                        textTwo = deliveryValue.toString()
                    )

                    Row(
                        modifier = Modifier
                            .width(350.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = stringResource(id = R.string.total),
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            text = "R$${formattedSum.replace(".", ",")}",
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black
                        )

                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Title(text = stringResource(id = R.string.payment_methods))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        androidx.compose.material3.ExposedDropdownMenuBox(
                            expanded = isExpanded,
                            onExpandedChange = { isExpanded = it },
                        ) {

                            OutlinedTextField(
                                modifier = Modifier
                                    .menuAnchor()
                                    .padding(start = 15.dp)
                                    .width(120.dp),
                                value = teste,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                                }
                            )

                            ExposedDropdownMenu(
                                expanded = isExpanded,
                                onDismissRequest = { isExpanded = false }
                            ) {

                                listFormPayment.forEach {

                                    androidx.compose.material3.DropdownMenuItem(
                                        text = {
                                            AsyncImage(
                                                modifier = Modifier
                                                    .size(20.dp),
                                                model = it.foto_bandeira,
                                                contentDescription = ""
                                            )
                                            Text(
                                                text = it.nome_forma_pagamento,
                                                color = Color.Black
                                            )
                                        },
                                        onClick = {
                                            var test3 = it.nome_forma_pagamento
                                            teste = test3
                                            onValueChange(it.nome_forma_pagamento)
                                            isExpanded = false
                                        }

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