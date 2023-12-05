package br.senai.sp.saveeats.productsrestaurant.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
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
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.ProductsRestaurant
import br.senai.sp.saveeats.model.ProductsRestaurantList
import br.senai.sp.saveeats.model.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ProductsRestaurantScreen(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current

    val nameRestaurant = localStorage.readDataString(context, "nameRestaurant")
    val imageRestaurant = localStorage.readDataString(context, "imageRestaurant")
    val nameCategoryRestaurant = localStorage.readDataString(context, "nameCategoryRestaurant")

    var listProductsRestaurant by remember {
        mutableStateOf(listOf<ProductsRestaurant>())
    }

    val callProductsRestaurant = RetrofitFactory
        .getProductsRestaurant()
        .getProductsRestaurant(nameRestaurant!!)

    callProductsRestaurant.enqueue(object : Callback<ProductsRestaurantList> {
        override fun onResponse(
            call: Call<ProductsRestaurantList>,
            response: Response<ProductsRestaurantList>
        ) {
            listProductsRestaurant = response.body()!!.produtos_do_restaurante
        }

        override fun onFailure(
            call: Call<ProductsRestaurantList>,
            t: Throwable
        ) {

            Log.i("ds3t", "onFailure: ${t.message}")

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
        ) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Surface(
                    modifier = Modifier
                        .height(140.dp)
                ) {

                    Image(
                        modifier = Modifier
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.baker),
                        contentDescription = "Image Background",
                        contentScale = ContentScale.FillWidth
                    )

                    IconButton(
                        modifier = Modifier
                            .offset(y = -(30).dp, x = 15.dp),
                        onClick = { navController.popBackStack() }) {

                        Surface(
                            modifier = Modifier
                                .size(30.dp),
                            color = Color(0, 0, 0, 160),
                            shape = CircleShape,

                            ) {

                            Column(
                                modifier = Modifier
                                    .offset(x = 10.5.dp, y = 8.dp)
                            ) {

                                Icon(
                                    modifier = Modifier
                                        .size(13.dp),
                                    imageVector = Icons.Default.ArrowBackIos,
                                    contentDescription = "Icon Back",
                                    tint = colorResource(id = R.color.white)
                                )

                            }

                        }

                    }

                }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = 125.dp),
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ) {

                }

            }

        }

    }

}