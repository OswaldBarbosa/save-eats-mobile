package br.senai.sp.saveeats.model

data class FormPayment(
    val id_restaurante_forma_pagamento: Int,
    val id: Int,
    val foto_bandeira: String,
    val nome_forma_pagamento: String,
    val tipo_forma_pagamento: String
)
