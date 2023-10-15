package br.senai.sp.saveeats.recipecomponents.screen.ui.theme

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.RecipeDetails
import br.senai.sp.saveeats.model.RecipeDetailsList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaveEatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeScreen()
                }
            }
        }
    }
}

@Composable
fun RecipeScreen() {

    val context = LocalContext.current

    var listRecipeDetails = remember {
        RecipeDetails(
            "",
            "",
            "",
            0,
            "",
            "",
            "",
            emptyList()
        )
    }

    var callRecipeDetails = RetrofitFactory
        .getRecipeDetails()
        .getRecipeDetails(1)

    callRecipeDetails.enqueue(object : Callback<RecipeDetailsList> {
        override fun onResponse(
            call: Call<RecipeDetailsList>,
            response: Response<RecipeDetailsList>
        ) {
            listRecipeDetails = response.body()!!.detalhes_receita
            Log.e("TESTE", "onResponse: ${listRecipeDetails}", )
        }

        override fun onFailure(
            call: Call<RecipeDetailsList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    Log.e("TESTE2", "RecipeScreen: ${listRecipeDetails.nome_receita}", )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(text = listRecipeDetails.nome_receita)

        }

    }

}