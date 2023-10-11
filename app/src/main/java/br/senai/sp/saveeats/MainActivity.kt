package br.senai.sp.saveeats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.categoryrestaurantcomponents.screen.CategoryRestaurantScreen
import br.senai.sp.saveeats.homecomponents.screen.HomeScreen
import br.senai.sp.saveeats.logincomponents.screen.LoginScreen
import br.senai.sp.saveeats.menubarcomponents.screen.MenuScreen
import br.senai.sp.saveeats.presentationcomponents.screen.FirstPresentationScreen
import br.senai.sp.saveeats.presentationcomponents.screen.SecondPresentationScreen
import br.senai.sp.saveeats.presentationcomponents.screen.ThirdPresentationScreen
import br.senai.sp.saveeats.productsrestaurantcomponents.screen.ProductsRestaurantScreen
import br.senai.sp.saveeats.singupcomponents.screen.SignupScreen
import br.senai.sp.saveeats.splashcomponents.screen.SplashScreen
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme
import br.senai.sp.saveeats.viewmodel.RestaurantViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaveEatsTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "second_presentation_screen"
                ) {

                    composable("splash_screen") {
                        SplashScreen(navController = navController)
                    }

                    composable("first_presentation_screen") {
                        FirstPresentationScreen(navController = navController)
                    }

                    composable("second_presentation_screen") {
                        SecondPresentationScreen(navController = navController)
                    }

                    composable("third_presentation_screen") {
                        ThirdPresentationScreen(navController = navController)
                    }

                    composable("login_screen") {
                        LoginScreen(navController = navController, lifecycleScope = lifecycleScope, localStorage = Storage())
                    }

                    composable("signup_screen") {
                        SignupScreen(navController = navController, lifecycleScope = lifecycleScope)
                    }

                    composable("home_screen") {
                        MenuScreen(navController2 = navController, lifecycle = lifecycleScope, viewModel = RestaurantViewModel())
                    }

                }

            }

        }

    }

}