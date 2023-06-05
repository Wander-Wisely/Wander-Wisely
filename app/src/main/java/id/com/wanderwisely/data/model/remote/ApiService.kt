package id.com.wanderwisely.data.model.remote

import id.com.wanderwisely.data.model.response.Current
import id.com.wanderwisely.data.model.response.WeatherResponse
import id.com.wanderwisely.data.model.response.WisataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("current")
    fun getWeather(
        @Query("access_key") accessKey :String,
        @Query("query") query : String
    ): Call<WeatherResponse>

    @GET("alls")
    suspend fun getWisata() : List<WisataResponse>
}