package br.senai.sp.saveeats.menubarcomponents.screen


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.saveeats.homecomponents.screen.HomeScreen
import br.senai.sp.saveeats.ordercomponents.OrderScreen
import br.senai.sp.saveeats.profilecomponents.screen.ProfileScreen
import br.senai.sp.saveeats.recipescomponents.screen.RecipesScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    navController2: NavHostController
) {
    NavHost(

        navController = navController,
        startDestination = MenuBar.Home.route

    ){

        composable(route = MenuBar.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = MenuBar.Historico.route) {
            OrderScreen()
        }

        composable(route = MenuBar.Receitas.route) {
            RecipesScreen()
        }

        composable(route = MenuBar.Perfil.route) {
           ProfileScreen()
        }

    }

}