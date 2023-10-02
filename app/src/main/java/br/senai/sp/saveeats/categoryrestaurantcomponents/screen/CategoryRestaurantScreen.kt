package br.senai.sp.saveeats.categoryrestaurantcomponents.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
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

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .offset(x = 30.dp, y = 58.dp)
            ) {

                Image(
                    modifier = Modifier
                        .size(15.dp),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Arrow"
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = nameCategory.uppercase(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400
                )

            }

        }

        LazyColumn() {

            items(listCategoryRestaurant) {

                Text(
                    text = it.nome_fantasia
                )

            }

        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Teste() {
    CategoryRestaurantScreen(nameCategory = "")
}