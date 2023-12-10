package br.senai.sp.saveeats.model

data class RestaurantOpeningHoursList(
    val status: Int,
    val message: String,
    val dias_horarios_funcionamento: List<RestaurantOpeningHours>
)
