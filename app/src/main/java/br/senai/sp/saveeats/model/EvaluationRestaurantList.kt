package br.senai.sp.saveeats.model

data class EvaluationRestaurantList(
    val status: String,
    val message: String,
    val quantidade_avaliacoes: Int,
    val media_estrelas: String,
    val avaliacoes_do_restaurante: List<EvaluationRestaurant>
)
