//package id.com.wanderwisely.data.model.remote.`interface`
//
//import id.com.wanderwisely.data.model.response.WeatherResponse
//import retrofit2.Call
//import retrofit2.http.GET
//import retrofit2.http.Query
//
//interface WeatherApiService {
//    @GET("current")
//    fun getWeather(
//        @Query("access_key") accessKey :String,
//        @Query("query") query : String
//    ): Call<WeatherResponse>
//}