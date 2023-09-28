package br.senai.sp.saveeats.menubarcomponents.screen

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.res.painterResource
import br.senai.sp.saveeats.R

sealed class MenuBar(
    val route: String,
    val title: String,
    val icon: Unit
){
    object Home: MenuBar(
        route = "home",
        title = "Home",
        icon = Image(painter = painterResource(id = R.drawable.home), contentDescription = "")
    )
    object Historico: MenuBar(
        route = "historico",
        title = "Historico",
        icon = Icons.Default.List
    )
    object Receitas: MenuBar(
        route = "receitas",
        title = "Receitas",
        icon = Icons.Default.DateRange
    )
    object Perfil: MenuBar(
        route = "perfil",
        title = "Perfil",
        icon = Icons.Default.Person
    )

}