package id.com.wanderwisely.data.model.remote.`interface`

import id.com.wanderwisely.data.model.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast")
    suspend fun getWeather(
        @Query("appid") Token_Api :String,
        @Query("q") query : String
    ): WeatherResponse
}