package br.senai.sp.saveeats.service

import br.senai.sp.saveeats.model.RecommendationList
import retrofit2.Call
import retrofit2.http.GET
interface RecommendationService {
    @GET("/v1/saveeats/recomendacao")
    fun getRecommendation(): Call<RecommendationList>

}