package br.senai.sp.saveeats.model

data class RecommendationList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val recomendacoes: List<Recommendation>
)
