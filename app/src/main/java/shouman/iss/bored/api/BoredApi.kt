package shouman.iss.bored.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BoredApi {
    val baseUrl: String = "https://www.boredapi.com/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}