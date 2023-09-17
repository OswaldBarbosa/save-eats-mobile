package br.senai.sp.saveeats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.saveeats.homecomponents.screen.HomeScreen
import br.senai.sp.saveeats.logincomponents.screen.LoginScreen
import br.senai.sp.saveeats.singupcomponents.screen.SignupScreen
import br.senai.sp.saveeats.ui.theme.SaveEatsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaveEatsTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login_screen"
                ) {

                    composable("login_screen") {
                        LoginScreen(navController = navController, lifecycleScope = lifecycleScope)
                    }

                    composable("signup_screen") {
                        SignupScreen(navController = navController, lifecycleScope = lifecycleScope)
                    }

                    composable("home_screen") {
                        HomeScreen(navController = navController)
                    }

                }

            }
        }
    }
}