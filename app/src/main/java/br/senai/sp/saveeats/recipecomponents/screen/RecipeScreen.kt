package br.senai.sp.saveeats.recipecomponents.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.saveeats.R
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

        val getImageRecipe = intent.getStringExtra("imageRecipe")

        val getNameRecipe = intent.getStringExtra("nameRecipe")

        val getTimeRecipe = intent.getStringExtra("timeRecipe")

        val getLevelRecipe = intent.getStringExtra("levelRecipe")

        val getPortionRecipe = intent.getIntExtra("portionRecipe", 0)

        val getDescriptionRecipe = intent.getStringExtra("descriptionRecipe")

        val getMethodOfPreparationRecipe = intent.getStringExtra("methodOfPreparationRecipe")

        super.onCreate(savedInstanceState)
        setContent {
            SaveEatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    RecipeScreen(
                        getImageRecipe.toString(),
                        getNameRecipe.toString(),
                        getTimeRecipe.toString(),
                        getLevelRecipe.toString(),
                        getPortionRecipe,
                        getDescriptionRecipe.toString(),
                        getMethodOfPreparationRecipe.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeScreen(
    imageRecipe: String,
    nameRecipe: String,
    timeRecipe: String,
    levelRecipe: String,
    portionRecipe: Int,
    descriptionRecipe: String,
    methodOfPreparation: String
) {

    var progressState by remember {
        mutableStateOf(true)
    }

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

    val callRecipeDetails = RetrofitFactory
        .getRecipeDetails()
        .getRecipeDetails(1)

    callRecipeDetails.enqueue(object : Callback<RecipeDetailsList> {
        override fun onResponse(
            call: Call<RecipeDetailsList>,
            response: Response<RecipeDetailsList>
        ) {
            listRecipeDetails = response.body()!!.detalhes_receita
        }

        override fun onFailure(
            call: Call<RecipeDetailsList>,
            t: Throwable
        ) {

            Log.e("ds3t", "onFailure: ${t.message}")

        }

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box {

                Icon(
                    modifier = Modifier
                        .offset(x = -(170.dp), y = 50.dp),
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Arrow Back",
                    tint = Color(20, 58, 11)
                )

            }

            Text(
                modifier = Modifier
                    .width(250.dp)
                    .offset(y = 20.dp),
                text = nameRecipe,
                fontSize = 22.sp,
                fontWeight = FontWeight.W500,
                color = Color(20, 58, 11),
                textAlign = TextAlign.Center
            )

        }

        Spacer(modifier = Modifier.height(55.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Row (
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
                        text = timeRecipe,
                        fontWeight = FontWeight.W300
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
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
                        text = "${portionRecipe} portions",
                        fontWeight = FontWeight.W300
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
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
                        text = levelRecipe,
                        fontWeight = FontWeight.W300
                    )

                }

            }

            Column {

                Surface (
                    modifier = Modifier
                        .size(180.dp),
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

        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Surface (
                modifier = Modifier
                    .fillMaxSize(),
                color = Color(246,246,246),
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
                        text = stringResource(id = R.string.description),
                        fontSize = 18.sp,
                        fontWeight = FontWeight(400)
                    )

                    Spacer(modifier = Modifier.width(40.dp))

                    Text(
                        modifier = Modifier
                            .clickable { progressState = false },
                        text = stringResource(id = R.string.method_of_preparation),
                        fontSize = 18.sp,
                        fontWeight = FontWeight(400)
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                if (progressState) {
                    
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = 20.dp, y = 80.dp)
                    ) {

                        Text(
                            text = stringResource(id = R.string.description),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(20,58,11)
                        )
                        
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            modifier = Modifier
                                .width(380.dp),
                            text = descriptionRecipe,
                            textAlign = TextAlign.Start
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = stringResource(id = R.string.ingredients),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(20,58,11)
                        )

                    }
                    
                } else {

                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = 20.dp, y = 80.dp)
                    ) {

                        Text(
                            text = methodOfPreparation,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(20,58,11)
                        )

                    }
                    
                }
                
            }

        }

    }

}