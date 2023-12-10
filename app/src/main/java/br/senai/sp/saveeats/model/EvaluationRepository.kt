package br.senai.sp.saveeats.model

import com.google.gson.JsonObject
import retrofit2.Response

class EvaluationRepository {

    private val service = RetrofitFactory
        .getEvaluation()
    suspend fun evaluation(idClient: Int, idRestaurant: Int, quantidadeEstrela: Int, descricao: String, dataAvaliacao: String, idRecomendacao: Int): Response<JsonObject> {

        val requestBody = JsonObject().apply {
            addProperty("cliente_id", idClient)
            addProperty("restaurante_id", idRestaurant)
            addProperty("quantidade_estrela", quantidadeEstrela)
            addProperty("descricao", descricao)
            addProperty("data_avaliacao", dataAvaliacao)
            addProperty("recomendacao_id", idRecomendacao)
        }

        return service.avaliation(requestBody)
    }

}