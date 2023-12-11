package br.senai.sp.saveeats.recipe.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.model.RecipeDetails
import br.senai.sp.saveeats.model.RecipeDetailsList
import br.senai.sp.saveeats.model.RecipeIngredients
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.recipe.components.HeaderRecipes
import br.senai.sp.saveeats.ui.theme.fontFamily
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RecipeScreen(
    navController: NavController,
    localStorage: Storage
) {

    val context = LocalContext.current

    val idRecipe = localStorage.readDataInt(context, "idRecipe")

    val imageRecipe = localStorage.readDataString(context, "imageRecipe")
    val descriptionRecipe = localStorage.readDataString(context, "descriptionRecipe")
    val portionRecipe = localStorage.readDataInt(context, "portionRecipe")
    val timeRecipe = localStorage.readDataString(context, "timeRecipe")
    val levelRecipe = localStorage.readDataString(context, "levelRecipe")
    val methodOfPreparation = localStorage.readDataString(context, "methodOfPreparationRecipe")

    var progressState by remember {
        mutableStateOf(true)
    }

    var listRecipeDetails by remember {
        mutableStateOf(
            RecipeDetails()
        )
    }

    var listRecipeIngredients by remember {
        mutableStateOf(
            listOf(
                RecipeIngredients(
                    0,
                    "",
                    "",
                    ""
                )
            )
        )
    }

    var status by remember {
        mutableStateOf(false)
    }

    val callCategoryTips = RetrofitFactory
        .getRecipeDetails()
        .getRecipeDetails(idRecipe)

    callCategoryTips.enqueue(object : Callback<RecipeDetailsList> {

        override fun onResponse(
            call: Call<RecipeDetailsList>,
            response: Response<RecipeDetailsList>
        ) {
            listRecipeDetails = response.body()!!.detalhes_receita
            listRecipeIngredients = response.body()!!.detalhes_receita.ingredientes!!
            status = true
        }

        override fun onFailure(
            call: Call<RecipeDetailsList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

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

            HeaderRecipes(text = listRecipeDetails.nome_receita!!, navController = navController)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Row(
                        modifier = Modifier
                            .width(110.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            painter = painterResource(id = R.drawable.clock),
                            contentDescription = "Icon Time"
                        )

                        Spacer(modifier = Modifier.width(15.dp))

                        Text(
                            text = timeRecipe!!,
                            fontWeight = FontWeight.W300
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .width(150.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(35.dp),
                            painter = painterResource(id = R.drawable.dish),
                            contentDescription = "Icon Dish"
                        )

                        Spacer(modifier = Modifier.width(15.dp))

                        Text(
                            text = "$portionRecipe portions",
                            fontWeight = FontWeight.W300
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .width(110.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(35.dp),
                            painter = painterResource(id = R.drawable.chef),
                            contentDescription = "Icon Chef"
                        )

                        Spacer(modifier = Modifier.width(15.dp))

                        Text(
                            text = levelRecipe!!,
                            fontWeight = FontWeight.W300
                        )

                    }

                }

                Column {

                    Surface(
                        modifier = Modifier
                            .size(160.dp),
                        shape = CircleShape,
                        shadowElevation = 10.dp
                    ) {

                        AsyncImage(
                            model = imageRecipe,
                            contentDescription = "Image Recipe",
                            contentScale = ContentScale.FillBounds
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(246, 246, 246),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    ) {

                        Text(
                            modifier = Modifier
                                .padding(start = 55.dp)
                                .clickable { progressState = true },
                            text = stringResource(id = R.string.description).uppercase(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = fontFamily
                        )

                        Spacer(modifier = Modifier.width(50.dp))

                        Text(
                            modifier = Modifier
                                .clickable { progressState = false },
                            text = stringResource(id = R.string.how_to_preparation).uppercase(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = fontFamily
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (progressState) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(x = 20.dp, y = 80.dp)
                        ) {

                            Text(
                                text = stringResource(id = R.string.description).uppercase(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = fontFamily,
                                color = colorResource(id = R.color.green_save_eats_light)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                modifier = Modifier
                                    .width(370.dp),
                                text = descriptionRecipe!!,
                                textAlign = TextAlign.Justify
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = stringResource(id = R.string.ingredients).uppercase(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = fontFamily,
                                color = colorResource(id = R.color.green_save_eats_light)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            LazyColumn (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 100.dp)
                            ) {

                                items(listRecipeIngredients) {
                                    
                                    Spacer(modifier = Modifier.height(10.dp))

                                    Row {

                                        AsyncImage(
                                            model = it.foto_ingrediente,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(45.dp)
                                                .border(
                                                    1.dp,
                                                    Color(41, 95, 27),
                                                    RoundedCornerShape(30)
                                                )
                                                .clip(RoundedCornerShape(30)),
                                            contentScale = ContentScale.Crop,

                                            )

                                        Spacer(modifier = Modifier.width(20.dp))

                                        Text(
                                            text = it.nome_ingrediente,
                                            fontFamily = fontFamily
                                        )

                                    }

                                }

                            }

                        }

                    } else {

                        Column(
                            modifier = Modifier
                                .offset(x = 20.dp,y = 80.dp)
                        ) {

                            Text(
                                modifier = Modifier
                                    .width(370.dp),
                                text = methodOfPreparation!!.replace(". ", ".\n\n"),
                                fontSize = 18.sp,
                                textAlign = TextAlign.Justify
                            )

                        }

                    }

                }

            }

        }

    }

}