package br.senai.sp.saveeats.singupcomponents.screen

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import br.senai.sp.saveeats.MainActivity
import br.senai.sp.saveeats.R
import br.senai.sp.saveeats.Retrofit
import br.senai.sp.saveeats.Service
import br.senai.sp.saveeats.components.InputOutlineTextField
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(navController: NavHostController, lifecycleScope: LifecycleCoroutineScope) {

    lateinit var service: Service

    service = Retrofit
        .getInstance()
        .create(Service::class.java)

    var context = LocalContext.current
    var focusManager = LocalFocusManager.current
    var scrollState = rememberScrollState()

    var name by rememberSaveable { mutableStateOf("") }
    var cpf by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var cep by rememberSaveable { mutableStateOf("") }
    var state by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var neighborhood by rememberSaveable { mutableStateOf("") }
    var street by rememberSaveable { mutableStateOf("") }
    var number by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var validateName by rememberSaveable { mutableStateOf(true) }
    var validateCpf by rememberSaveable { mutableStateOf(true) }
    var validatePhone by rememberSaveable { mutableStateOf(true) }
    var validateCep by rememberSaveable { mutableStateOf(true) }
    var validateState by rememberSaveable { mutableStateOf(true) }
    var validateCity by rememberSaveable { mutableStateOf(true) }
    var validateNeighborhood by rememberSaveable { mutableStateOf(true) }
    var validateStreet by rememberSaveable { mutableStateOf(true) }
    var validateNumber by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var validateConfirmPassword by rememberSaveable { mutableStateOf(true) }
    var validatearePasswordsEqual by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisibible by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateNameError = stringResource(id = R.string.name_error)
    val validateCpfError = stringResource(id = R.string.cpf_error)
    val validateCepError = stringResource(id = R.string.cep_error)
    val validateStateError = stringResource(id = R.string.state_error)
    val validateCityError = stringResource(id = R.string.city_error)
    val validateNeighborhoodError = stringResource(id = R.string.neighborhood_error)
    val validateStreetError = stringResource(id = R.string.street_error)
    val validateNumberError = stringResource(id = R.string.number_error)
    val validateEmailError = stringResource(id = R.string.email_error)
    val validatePhoneError = stringResource(id = R.string.phone_error)
    val validatePasswordError = stringResource(id = R.string.password_error)
    val validateEqualPasswordError = stringResource(id = R.string.password_equal_error)

    fun validateData(
        name: String,
        cpf: String,
        cep: String,
        state: String,
        city: String,
        neighborhood: String,
        street: String,
        number: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ): Boolean {

        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%ˆ&+-]).{8,}".toRegex()

        validateName = name.isNotBlank()
        validateCpf = cpf.isNotBlank()
        validateCep = cep.isNotBlank()
        validateState = state.isNotBlank()
        validateCity = city.isNotBlank()
        validateNeighborhood = neighborhood.isNotBlank()
        validateStreet = street.isNotBlank()
        validateNumber = number.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePhone = Patterns.PHONE.matcher(phone).matches()
        validatePassword = passwordRegex.matches(password)
        validateConfirmPassword = passwordRegex.matches(confirmPassword)
        validatearePasswordsEqual = password == confirmPassword

        return validateName && validateCpf && validatePhone && validateCep && validateEmail && validatePassword && validateConfirmPassword && validateConfirmPassword && validatearePasswordsEqual

    }

    fun signup(
        name: String,
        cpf: String,
        cep: String,
        state: String,
        city: String,
        neighborhood: String,
        street: String,
        number: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ) {

        if (validateData(
                name,
                cpf,
                cep,
                state,
                city,
                neighborhood,
                street,
                number,
                email,
                phone,
                password,
                confirmPassword
            )
        ) {

            val signup = SignupRepository()

            lifecycleScope.launch {

                val response = signup.signupClient(
                    name,
                    cpf,
                    cep,
                    state,
                    city,
                    neighborhood,
                    street,
                    number,
                    email,
                    phone,
                    password
                )

                if (response.isSuccessful) {

                    Toast.makeText(context, "Faça login", Toast.LENGTH_SHORT).show()
                    navController.navigate("login_screen")

                } else {

                    val errorBody = response.errorBody()?.string()

                    Log.e(MainActivity::class.java.simpleName, "Erro durante o login: $errorBody")
                    Toast.makeText(context, "Esse e-mail já está vinculado a uma conta", Toast.LENGTH_SHORT).show()

                }

            }

        } else {
            Toast.makeText(context, "Please, review fields", Toast.LENGTH_SHORT).show()
        }

    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        Alignment.Center
                    ) {

                        Image(
                            modifier = Modifier
                                .size(200.dp)
                                .offset(x = -(185).dp, y = -(100).dp),
                            painter = painterResource(id = R.drawable.prato),
                            contentDescription = "Prato de comida"
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = stringResource(id = R.string.signup).uppercase(),
                                color = Color(41, 95, 27),
                                fontWeight = FontWeight(700),
                                fontSize = 34.sp,

                                )

                            Text(
                                text = stringResource(id = R.string.create_your_account),
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            )

                        }

                    }

                }

                InputOutlineTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = stringResource(id = R.string.name),
                    showError = !validateName,
                    errorMessage = validateNameError,
                    leadingIconImageVector = Icons.Default.Person,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = cpf,
                    onValueChange = { cpf = it },
                    label = stringResource(id = R.string.cpf),
                    showError = !validateCpf,
                    errorMessage = validateCpfError,
                    leadingIconImageVector = Icons.Default.Numbers,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = stringResource(id = R.string.phone),
                    showError = !validatePhone,
                    errorMessage = validatePhoneError,
                    leadingIconImageVector = Icons.Default.Phone,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = cep,
                    onValueChange = { cep = it },
                    label = stringResource(id = R.string.cep),
                    showError = !validateCep,
                    errorMessage = validateCepError,
                    leadingIconImageVector = Icons.Default.LocationOn,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = state,
                    onValueChange = { state = it },
                    label = stringResource(id = R.string.state),
                    showError = !validateState,
                    errorMessage = validateStateError,
                    leadingIconImageVector = Icons.Default.LocationCity,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = stringResource(id = R.string.city),
                    showError = !validateCity,
                    errorMessage = validateCityError,
                    leadingIconImageVector = Icons.Default.LocationCity,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = neighborhood,
                    onValueChange = { neighborhood = it },
                    label = stringResource(id = R.string.neighborhood),
                    showError = !validateNeighborhood,
                    errorMessage = validateNeighborhoodError,
                    leadingIconImageVector = Icons.Default.LocationCity,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = street,
                    onValueChange = { street = it },
                    label = stringResource(id = R.string.street),
                    showError = !validateStreet,
                    errorMessage = validateStreetError,
                    leadingIconImageVector = Icons.Default.LocationCity,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = number,
                    onValueChange = { number = it },
                    label = stringResource(id = R.string.number),
                    showError = !validateNumber,
                    errorMessage = validateNumberError,
                    leadingIconImageVector = Icons.Default.Numbers,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = stringResource(id = R.string.email),
                    showError = !validateEmail,
                    errorMessage = validateEmailError,
                    leadingIconImageVector = Icons.Default.AlternateEmail,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(id = R.string.password),
                    showError = !validatePassword,
                    errorMessage = validatePasswordError,
                    isPasswordField = true,
                    isPasswordVisible = isPasswordVisibible,
                    onVisibilityChange = { isPasswordVisibible = it },
                    leadingIconImageVector = Icons.Default.Password,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

                InputOutlineTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = stringResource(id = R.string.confirm_password),
                    showError = !validateConfirmPassword || !validatearePasswordsEqual,
                    errorMessage = if (!validateConfirmPassword) validatePasswordError else validateEqualPasswordError,
                    isPasswordField = true,
                    isPasswordVisible = isConfirmPasswordVisible,
                    onVisibilityChange = { isConfirmPasswordVisible = it },
                    leadingIconImageVector = Icons.Default.Password,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    borderColor = Color(72, 138, 39),
                    border = ShapeDefaults.Small
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Button(
                    modifier = Modifier
                        .height(60.dp)
                        .width(200.dp),
                    onClick = {
                        signup(
                            name,
                            cpf,
                            cep,
                            state,
                            city,
                            neighborhood,
                            street,
                            number,
                            email,
                            phone,
                            password,
                            confirmPassword
                        )
                    },
                    colors = ButtonDefaults.buttonColors(Color(255, 141, 6))

                ) {

                    Text(
                        text = stringResource(id = R.string.signup),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

            }

        }

    }

}