package br.senai.sp.saveeats

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {

    //cadastro cliente
    @Headers("Content-Type: application/json")
    @POST("v1/saveeats/cadastro/cliente")
    suspend fun signupClient(@Body body: JsonObject): Response<JsonObject>

    //login cliente
    @Headers("Content-Type: application/json")
    @POST("v1/saveeats/cliente/login/autenticar")
    suspend fun authenticationClient(@Body body: JsonObject): Response<JsonObject>

}