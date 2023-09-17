package br.senai.sp.saveeats.logincomponents.screen

import br.senai.sp.saveeats.Retrofit
import br.senai.sp.saveeats.Service
import com.google.gson.JsonObject
import retrofit2.Response

class LoginRepository {

    private val service = Retrofit
        .getInstance()
        .create(Service::class.java)

    suspend fun loginClient(email: String, password: String) : Response<JsonObject> {
        val requestBody = JsonObject().apply {

            addProperty("email", email)
            addProperty("senha", password)

        }

        return service.authenticationClient(requestBody)

    }

}