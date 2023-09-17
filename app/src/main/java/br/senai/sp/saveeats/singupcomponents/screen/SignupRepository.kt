package br.senai.sp.saveeats.singupcomponents.screen

import br.senai.sp.saveeats.Retrofit
import br.senai.sp.saveeats.Service
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.create

class SignupRepository {

    private val service = Retrofit
        .getInstance()
        .create(Service::class.java)

    suspend fun signupClient(name: String, cpf: String, cep: String, state: String, city: String, neighborhood: String, street: String, number: String, email: String, phone: String, password: String): Response<JsonObject> {

        val requestBody = JsonObject().apply {

            addProperty("nome", name)
            addProperty("cpf", cpf)
            addProperty("cep", cep)
            addProperty("nome_estado", state)
            addProperty("nome_cidade", city)
            addProperty("bairro", neighborhood)
            addProperty("rua", street)
            addProperty("numero", number)
            addProperty("email", email)
            addProperty("telefone", phone)
            addProperty("senha", password)

        }

        return service.signupClient(requestBody)

    }

}