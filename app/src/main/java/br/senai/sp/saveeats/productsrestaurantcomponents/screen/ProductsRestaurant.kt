package br.senai.sp.saveeats.productsrestaurantcomponents.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.senai.sp.saveeats.model.ProductsRestaurant
import br.senai.sp.saveeats.model.ProductsRestaurantList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRestaurantScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getNameRestaurant = intent.getStringExtra("name_restaurant")

        setContent {
            SaveEatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductsRestaurantScreen(
                        getNameRestaurant.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun ProductsRestaurantScreen(nameRestaurant: String) {

    var listProductsRestaurant by remember {
        mutableStateOf(listOf<ProductsRestaurant>())
    }

    //cria uma chamada para o endpoint
    var callProductsRestaurant = RetrofitFactory
        .getProductsRestaurant()
        .getProductsRestaurant(nameRestaurant)

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

    LazyColumn() {

        items(listProductsRestaurant) {

            Card (
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            ) {

                Text(
                    text = it.nome,
                    color = Color.Black
                )

            }

        }

    }

}