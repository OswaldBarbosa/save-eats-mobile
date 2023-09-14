package br.senai.sp.saveeats.logincomponents.screen.ui.theme

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.components.CustomButton
import br.senai.sp.saveeats.components.CustomOutlineTextField
import br.senai.sp.saveeats.logincomponents.screen.ui.theme.ui.theme.SaveEatsTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaveEatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {

    var context = LocalContext.current
    var focusManager = LocalFocusManager.current
    var scrollState = rememberScrollState()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }

    val validateEmailError = "The format of the email doesn't seem right"
    val validatePasswordError = "Must mix capital and non-capital letters, a number, special character and a minium legth of 8"

    fun validateData(email: String, password: String): Boolean {

        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%ˆ&+-]).{8,}".toRegex()

        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePassword = passwordRegex.matches(password)

        return validateEmail && validatePassword

    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                Alignment.BottomCenter
            ) {

                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .offset(x = -165.dp, y = -155.dp),
                    painter = painterResource(id = R.drawable.prato),
                    contentDescription = "Prato de comida"
                )

                Image(
                    modifier = Modifier
                        .size(200.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo"
                )

            }

            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.welcome),
                    color = Color(41, 95, 27),
                    fontWeight = FontWeight(700),
                    fontSize = 36.sp
                )

                Text(
                    text = stringResource(id = R.string.enter_account),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                )

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                Alignment.Center
            ) {

                Image(
                    modifier = Modifier
                        .size(180.dp)
                        .offset(x = -180.dp, y = 60.dp),
                    painter = painterResource(id = R.drawable.hamburguer),
                    contentDescription = "Hamburguer"
                )

                Image(
                    modifier = Modifier
                        .size(250.dp)
                        .offset(x = 150.dp, y = -100.dp),
                    painter = painterResource(id = R.drawable.pao),
                    contentDescription = "Pão"
                )

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    CustomOutlineTextField(
                        value = email,
                        onValueChange = { email = it},
                        label = stringResource(id = R.string.email),
                        showError = !validateEmail,
                        errorMessage = validateEmailError,
                        leadingIconImageVector = Icons.Default.AlternateEmail,
                        borderColor = Color(72, 138, 39),
                        border = ShapeDefaults.Small
                    )

                    CustomOutlineTextField(
                        value = password,
                        onValueChange = { password = it},
                        label = stringResource(id = R.string.password),
                        showError = !validatePassword,
                        errorMessage = validatePasswordError,
                        leadingIconImageVector = Icons.Default.Password,
                        borderColor = Color(72, 138, 39),
                        border = ShapeDefaults.Small
                    )

                    CustomButton(
                        navController = navController,
                        textButton = stringResource(id = R.string.login))

                }

            }

        }

    }

}