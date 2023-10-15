package br.senai.sp.saveeats.recipestipscomponents.screen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.CategoryRecipes
import br.senai.sp.saveeats.model.CategoryRecipesList
import br.senai.sp.saveeats.model.Recipes
import br.senai.sp.saveeats.model.RecipesList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.recipecomponents.screen.ui.theme.MainActivity2
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RecipesTipsScreen(localStorage: Storage) {

    var context = LocalContext.current

    var progressState by remember {
        mutableStateOf(true)
    }

    var searchState by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var listRecipes by remember {
        mutableStateOf(listOf<Recipes>())
    }

    var listCategoryRecipes by remember {
        mutableStateOf(listOf<CategoryRecipes>())
    }

    var callRecipes = RetrofitFactory
        .getRecipes()
        .getRecipes()

    callRecipes.enqueue(object : Callback<RecipesList> {

        override fun onResponse(
            call: Call<RecipesList>,
            response: Response<RecipesList>
        ) {
            listRecipes = response.body()!!.receitas
        }

        override fun onFailure(
            call: Call<RecipesList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    var callCategoryRecipes = RetrofitFactory
        .getCategoryRecipes()
        .getCategoryRecipes()

    callCategoryRecipes.enqueue(object : Callback<CategoryRecipesList> {

        override fun onResponse(
            call: Call<CategoryRecipesList>,
            response: Response<CategoryRecipesList>
        ) {
            listCategoryRecipes = response.body()!!.categorias
        }

        override fun onFailure(
            call: Call<CategoryRecipesList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.recipes_and_tips),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W300,
                    color = Color(76, 77, 76)
                )

            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 55.dp)
                        .clickable { progressState = true },
                    text = stringResource(id = R.string.recipes),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400)
                )

                Spacer(modifier = Modifier.width(170.dp))

                Text(
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .clickable { progressState = false },
                    text = stringResource(id = R.string.tips),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400)
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(
                        if (progressState == false) {
                            Color(41, 95, 27)
                        } else {
                            Color(191, 193, 198, 255)
                        },
                        shape = RoundedCornerShape(size = 5.dp)
                    )

            ) {

                Row(
                    modifier = Modifier
                        .width(190.dp)
                        .height(2.dp)
                        .background(
                            color = if (progressState == true) {
                                Color(41, 95, 27)
                            } else {
                                Color(191, 193, 198, 255)
                            },
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                ) {}

            }

            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .width(360.dp)
                        .height(45.dp),
                    value = searchState,
                    onValueChange = {
                        searchState = it
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = "Pesquisar",
                        )
                    },
                    shape = RoundedCornerShape(100)

                )

            }

            Spacer(modifier = Modifier.height(25.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                items(listCategoryRecipes) {

                    Surface(
                        modifier = Modifier
                            .width(120.dp)
                            .height(40.dp)
                            .padding(end = 20.dp),
                        shape = RoundedCornerShape(30.dp),
                        border = BorderStroke(0.8.dp, Color(212, 212, 212))
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(text = it.categoria)

                        }

                    }

                }

            }

            Spacer(modifier = Modifier.height(25.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                items(listRecipes) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Surface(
                            modifier = Modifier
                                .width(160.dp)
                                .height(160.dp)
                                .padding(20.dp)
                                .clickable {
                                    var openRecipe = Intent(context, MainActivity2()::class.java)
                                    localStorage.saveDataInt(context, it.id_receita, "idRecipe")
                                    context.startActivity(openRecipe)
                                },
                            shape = RoundedCornerShape(20.dp),
                            elevation = 10.dp
                        ) {

                            AsyncImage(
                                model = it.foto_receita,
                                contentDescription = "Image Recipes"
                            )

                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            Text(
                                text = it.nome_receita,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500
                            )

                            Text(
                                text = it.tempo_preparo,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400
                            )

                        }

                    }

                }

            }

        }

    }

}