package br.senai.sp.saveeats.model

data class RestaurantList(
    val status: Int,
    val message: String,
    val quantidade: String,
    val restaurantes: List<Restaurant>
)
