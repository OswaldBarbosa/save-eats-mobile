package br.senai.sp.saveeats.categoryrestaurantcomponents.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.senai.sp.saveeats.model.CategoryRestaurant
import br.senai.sp.saveeats.model.CategoryRestaurantList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRestaurantScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getCategoryRestaurant = intent.getStringExtra("name_category")

        setContent {
            SaveEatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CategoryRestaurantScreen(
                        getCategoryRestaurant.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryRestaurantScreen(nameCategory: String) {

    var listCategoryRestaurant by remember {
        mutableStateOf(listOf<CategoryRestaurant>())
    }

    //cria uma chamada para o endpoint
    var callCategoryRestaurant = RetrofitFactory
        .getCategoryRestaurant()
        .getCategoryRestaurant(nameCategory)

    callCategoryRestaurant.enqueue(object : Callback<CategoryRestaurantList> {
        override fun onResponse(
            call: Call<CategoryRestaurantList>,
            response: Response<CategoryRestaurantList>
        ) {
            listCategoryRestaurant = response.body()!!.restaurantes_da_categoria_escolhida
        }

        override fun onFailure(
            call: Call<CategoryRestaurantList>,
            t: Throwable
        ) {

            Log.i("ds3t", "onFailure: ${t.message}")

        }

    })

    LazyColumn() {

        items(listCategoryRestaurant) {

            Text(
                text = it.nome_fantasia
            )

        }

    }

}