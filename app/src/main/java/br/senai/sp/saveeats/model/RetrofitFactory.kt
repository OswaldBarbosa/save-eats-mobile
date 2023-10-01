package br.senai.sp.saveeats.model

import br.senai.sp.saveeats.service.CategoryRestaurantService
import br.senai.sp.saveeats.service.CategoryService
import br.senai.sp.saveeats.service.LoginService
import br.senai.sp.saveeats.service.ProductsRestaurantService
import br.senai.sp.saveeats.service.RestaurantService
import br.senai.sp.saveeats.service.SignupService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val baseURL = "https://save-eats.cyclic.cloud/"

    private var retrofitFactory = Retrofit
        .Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getSignup(): SignupService {
        return retrofitFactory.create(SignupService::class.java)
    }
    fun getLogin(): LoginService {
        return retrofitFactory.create(LoginService::class.java)
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

}