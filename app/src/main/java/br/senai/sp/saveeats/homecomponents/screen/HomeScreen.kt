package br.senai.sp.saveeats.homecomponents.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.components.SearchOutlineTextField

@Composable
fun HomeScreen(navController: NavHostController) {

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
                        tint = Color(93, 182, 49)
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
                    tint = Color(93, 182, 49)
                )

            }

            Column (
                modifier = Modifier
            ) {

                SearchOutlineTextField(
                    value = searh,
                    onValueChange = {} ,
                    label = stringResource(id = R.string.search),
                    leadingIconImageVector = Icons.Default.Search,
                    borderColor = Color(85,85,85),
                    border = ShapeDefaults.Large,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    })
                )

            }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen() {
    HomeScreen(navController = rememberNavController())
}