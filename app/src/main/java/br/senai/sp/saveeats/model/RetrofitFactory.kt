package br.senai.sp.saveeats.model

import br.senai.sp.saveeats.service.CategoryRestaurantService
import br.senai.sp.saveeats.service.CategoryService
import br.senai.sp.saveeats.service.ClientService
import br.senai.sp.saveeats.service.DeliveryAreaService
import br.senai.sp.saveeats.service.EditProfileService
import br.senai.sp.saveeats.service.EvaluationService
import br.senai.sp.saveeats.service.FormPaymentService
import br.senai.sp.saveeats.service.HistoricService
import br.senai.sp.saveeats.service.LoginService
import br.senai.sp.saveeats.service.OrderService
import br.senai.sp.saveeats.service.ProductsRestaurantService
import br.senai.sp.saveeats.service.RecipesService
import br.senai.sp.saveeats.service.RecommendationService
import br.senai.sp.saveeats.service.RestaurantService
import br.senai.sp.saveeats.service.SignupService
import br.senai.sp.saveeats.service.TipsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val baseURL = "https://save-eats-backend.azurewebsites.net/"

    private const val baseViaCepURL = "https://viacep.com.br/"

    private var retrofitFactory = Retrofit
        .Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseViaCepURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getSignup(): SignupService {
        return retrofitFactory.create(SignupService::class.java)
    }

    fun getLogin(): LoginService {
        return retrofitFactory.create(LoginService::class.java)
    }

    fun getOrder(): OrderService {
        return retrofitFactory.create(OrderService::class.java)
    }

    fun getCategory(): CategoryService {
        return retrofitFactory.create(CategoryService::class.java)
    }

    fun getRestaurant(): RestaurantService {
        return retrofitFactory.create(RestaurantService::class.java)
    }

    fun getProductsRestaurant(): ProductsRestaurantService {
        return retrofitFactory.create(ProductsRestaurantService::class.java)
    }

    fun getCategoryRestaurant(): CategoryRestaurantService {
        return retrofitFactory.create(CategoryRestaurantService::class.java)
    }

    fun getAddressClient(): ClientService {
        return retrofitFactory.create(ClientService::class.java)
    }

    fun getRecipes(): RecipesService {
        return retrofitFactory.create(RecipesService::class.java)
    }

    fun getCategoryRecipes(): RecipesService {
        return retrofitFactory.create(RecipesService::class.java)
    }

    fun getRecipeDetails(): RecipesService {
        return retrofitFactory.create(RecipesService::class.java)
    }

    fun getTips(): TipsService {
        return retrofitFactory.create(TipsService::class.java)
    }

    fun getCategoryTips(): TipsService {
        return retrofitFactory.create(TipsService::class.java)
    }

    fun getDeliveryArea(): DeliveryAreaService {
        return retrofitFactory.create(DeliveryAreaService::class.java)
    }

    fun getFormPayment(): FormPaymentService {
        return retrofitFactory.create(FormPaymentService::class.java)
    }

    fun getHistoricById(): HistoricService{
        return  retrofitFactory.create(HistoricService::class.java)
    }

    fun getOrderById(): OrderService{
        return  retrofitFactory.create(OrderService::class.java)
    }

    fun getEditProfile(): EditProfileService{
        return  retrofitFactory.create(EditProfileService::class.java)
    }
    fun getAddressByIdRestaurant() : RestaurantService{
        return  retrofitFactory.create(RestaurantService::class.java)
    }
    fun getOpeningHoursByIdRestaurant() : RestaurantService{
        return  retrofitFactory.create(RestaurantService::class.java)
    }
    fun getEvaluationByIdRestaurant(): RestaurantService{
        return  retrofitFactory.create(RestaurantService::class.java)
    }
    fun getEvaluation(): EvaluationService{
        return  retrofitFactory.create(EvaluationService::class.java)
    }
    fun getRecommendation(): RecommendationService{
        return retrofitFactory.create(RecommendationService::class.java)
    }

}