package br.senai.sp.saveeats.homecomponents.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.components.SearchOutlineTextField

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val imageSlide = remember {
        mutableStateListOf(
            R.drawable.paes,
            R.drawable.restaurante,
            R.drawable.livro_de_receitas
        )
    }

    val textSlide = remember {
        mutableStateListOf(
            R.string.slide_one,
            R.string.slide_two,
            R.string.slide_three
        )
    }

    val colorsSlide = remember {
        mutableStateListOf(
            R.color.slide_one,
            R.color.slide_two,
            R.color.slide_three,
        )
    }

    val pagerState = rememberPagerState(
        pageCount = {
            3
        }
    )


    val focusManager = LocalFocusManager.current

    var searh by rememberSaveable { mutableStateOf("") }

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
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier
                        .size(60.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo"
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(25.dp),
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Location",
                        tint = Color(76, 132, 62)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Rua Elton Silva 95, Jandira",
                        fontSize = 17.sp

                    )

                }

                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    painter = painterResource(id = R.drawable.carrinho),
                    contentDescription = "Carrinho de Compras",
                    tint = Color(76, 132, 62)
                )

            }

            Column(
                modifier = Modifier
            ) {

                SearchOutlineTextField(
                    value = searh,
                    onValueChange = {},
                    label = stringResource(id = R.string.search),
                    leadingIconImageVector = Icons.Default.Search,
                    borderColor = Color(85, 85, 85),
                    border = RoundedCornerShape(10.dp),
                    tint = Color(76, 132, 62),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    })
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 25.dp, bottom = 10.dp),
                    text = stringResource(id = R.string.categories),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(20, 58, 11),
                    letterSpacing = 2.sp
                )

                LazyRow(
                    modifier = Modifier
                        .padding(start = 25.dp)
                ) {

                    items(5) {

                        Card(
                            modifier = Modifier
                                .width(160.dp)
                                .height(45.dp)
                                .padding(end = 15.dp),
                            border = BorderStroke(0.8.dp, Color(212,212,212)),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )

                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                Image(
                                    modifier = Modifier
                                        .size(50.dp),
                                    painter = painterResource(id = R.drawable.logo),
                                    contentDescription = "teste"
                                )

                                Text(
                                    text = "Mercado",
                                    color = Color.Black
                                )

                            }

                        }

                    }

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    modifier = Modifier
                        .width(360.dp)
                        .height(200.dp)
                ) {

                    HorizontalPager(
                        state = pagerState,
                        pageSpacing = 0.dp,
                        pageContent = {

                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(colorResource(id = colorsSlide[it])),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    modifier = Modifier
                                        .width(160.dp),
                                    text = stringResource(id = textSlide[it]),
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                )

                                Image(
                                    modifier = Modifier
                                        .size(120.dp),
                                    painter = painterResource(id = imageSlide[it]),
                                    contentDescription = ""
                                )

                            }

                        }

                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Card(
                    modifier = Modifier
                        .width(18.dp)
                        .height(12.dp),
                    colors =

                    if (pagerState.currentPage == 0) {

                        CardDefaults.cardColors(
                            containerColor = Color(76, 132, 62)
                        )

                    } else {

                        CardDefaults.cardColors(
                            containerColor = Color(217, 217, 217)
                        )
                    }

                ) {}

                Spacer(modifier = Modifier.width(25.dp))

                Card(
                    modifier = Modifier
                        .width(18.dp)
                        .height(12.dp),
                    colors =

                    if (pagerState.currentPage == 1) {

                        CardDefaults.cardColors(
                            containerColor = Color(76, 132, 62)
                        )

                    } else {

                        CardDefaults.cardColors(
                            containerColor = Color(217, 217, 217)
                        )

                    }

                ) {}

                Spacer(modifier = Modifier.width(25.dp))

                Card(
                    modifier = Modifier
                        .width(18.dp)
                        .height(12.dp),
                    colors =

                    if (pagerState.currentPage == 2) {

                        CardDefaults.cardColors(
                            containerColor = Color(76, 132, 62)
                        )

                    } else {

                        CardDefaults.cardColors(
                            containerColor = Color(217, 217, 217)
                        )

                    }

                ) {}

            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 25.dp, bottom = 10.dp, top = 20.dp),
                    text = stringResource(id = R.string.restaurant),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(20, 58, 11),
                    letterSpacing = 2.sp
                )

            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                items(6) {

                    Surface (
                        modifier = Modifier
                            .width(380.dp)
                            .height(70.dp)
                            .padding(start = 25.dp, bottom = 10.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(0.8.dp, color = Color(212,212,212))
                    ) {

                        Text(text = "teste")

                    }

                }

            }

        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun teste() {
    HomeScreen(navController = rememberNavController())
}