package br.senai.sp.saveeats.model

data class ClientAddress(
    val id: Int,
    val rua: String,
    val cep: String,
    val bairro: String,
    val numero: Int,
    val complemento: String,
    val nome_cidade: String
)
