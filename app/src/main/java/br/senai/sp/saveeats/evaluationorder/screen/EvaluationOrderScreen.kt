package br.senai.sp.saveeats.evaluationorder.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Storage
import br.senai.sp.saveeats.components.Header
import br.senai.sp.saveeats.model.EvaluationRepository
import br.senai.sp.saveeats.model.Recommendation
import br.senai.sp.saveeats.model.RecommendationList
import br.senai.sp.saveeats.model.RetrofitFactory
import br.senai.sp.saveeats.ui.theme.fontFamily
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluationScreen(
    navController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope
) {

    val context = LocalContext.current

    val idClient = localStorage.readDataInt(context, "idClient")
    val idRestaurant = localStorage.readDataInt(context, "idRestaurant")

    val sdf = SimpleDateFormat("yyyy/MM/dd")

    val currentDate = sdf.format(Date())

    var listRecommendation by remember {
        mutableStateOf(
            listOf<Recommendation>()
        )
    }

    var evaluationState by remember {
        mutableStateOf("")
    }

    var quantityStar by remember {
        mutableIntStateOf(0)
    }

    var idRecommendation by remember {
        mutableIntStateOf(0)
    }

    val callRecommendation = RetrofitFactory
        .getRecommendation()
        .getRecommendation()

    callRecommendation.enqueue(object : retrofit2.Callback<RecommendationList> {
        override fun onResponse(
            call: Call<RecommendationList>,
            response: Response<RecommendationList>
        ) {
            listRecommendation = response.body()!!.recomendacoes

        }

        override fun onFailure(call: Call<RecommendationList>, t: Throwable) {
            Log.e("error", "onFailure: $t")
        }

    })

    fun evaluation(
        idCliente: Int,
        idRestaurante: Int,
        quantidadeEstrela: Int,
        descricao: String,
        dataAvaliacao: String,
        recomendacaoId: Int
    ) {

        val evaluate = EvaluationRepository()

        lifecycleScope.launch {

            val response = evaluate.evaluation(
                idCliente,
                idRestaurante,
                quantidadeEstrela,
                descricao,
                dataAvaliacao,
                recomendacaoId
            )

            if (response.isSuccessful) {

                navController.navigate("home_screen")

            } else {
                Log.e("ERROR", "${response.body()}")
            }

        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Header(text = stringResource(id = R.string.evaluations), navController = navController)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 20.dp)
        ) {

            Text(
                text = stringResource(id = R.string.what_did_you_think_of_your_order),
                fontSize = 20.sp,
                fontFamily = fontFamily
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(id = R.string.choose_from_1_to_5_stars),
                fontSize = 16.sp,
                fontFamily = fontFamily
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .width(120.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            quantityStar = 1
                        },
                    tint = if (quantityStar < 1) {
                        Color(126, 126, 126, 255)
                    } else {
                        Color(255, 229, 0, 255)
                    }
                )

                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { quantityStar = 2 },
                    tint = if (quantityStar < 2) {
                        Color(126, 126, 126, 255)
                    } else {
                        Color(255, 229, 0, 255)
                    }
                )

                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { quantityStar = 3 },
                    tint = if (quantityStar < 3) {
                        Color(126, 126, 126, 255)
                    } else {
                        Color(255, 229, 0, 255)
                    }
                )

                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { quantityStar = 4 },
                    tint = if (quantityStar < 4) {
                        Color(126, 126, 126, 255)
                    } else {
                        Color(255, 229, 0, 255)
                    }
                )

                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { quantityStar = 5 },
                    tint = if (quantityStar < 5) {
                        Color(126, 126, 126, 255)
                    } else {
                        Color(255, 229, 0, 255)
                    }
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.what_can_we_improve),
                fontSize = 18.sp,
                fontWeight = FontWeight(500)
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                modifier = Modifier
                    .width(370.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {

                items(listRecommendation) {

                    Card(
                        modifier = Modifier
                            .height(50.dp)
                            .clickable {
                                idRecommendation = it.id
                            },

                        colors = CardDefaults.cardColors(
                            if (idRecommendation == it.id) {
                                colorResource(id = R.color.green_save_eats_light)
                            } else {
                                Color(214, 215, 216)
                            }
                        ),
                        shape = RoundedCornerShape(80)
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )

                        {

                            Text(
                                text = it.recomendacao,
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                color = Color.White
                            )

                        }

                    }

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = evaluationState,
                onValueChange = {
                    evaluationState = it
                },
                modifier = Modifier
                    .width(370.dp)
                    .height(200.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.write_your_comment)
                    )
                }
            )

            Spacer(modifier = Modifier.height(50.dp))

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            androidx.compose.material.Button(
                onClick = {

                    evaluation(
                        idClient,
                        idRestaurant,
                        quantityStar,
                        evaluationState,
                        currentDate,
                        idRecommendation
                    )

                },
                modifier = Modifier
                    .width(255.dp)
                    .height(55.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(76, 132, 62)
                )
            ) {

                Text(
                    text = stringResource(id = R.string.send_evaluations).uppercase(),
                    fontSize = 20.sp,
                    fontFamily = fontFamily,
                    color = Color(255, 255, 255)
                )

            }

        }

    }

}