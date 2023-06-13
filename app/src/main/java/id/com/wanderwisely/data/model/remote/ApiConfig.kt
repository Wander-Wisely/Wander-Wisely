package id.com.wanderwisely.data.model.remote

import id.com.wanderwisely.BuildConfig
import id.com.wanderwisely.data.model.remote.`interface`.RecommendApiService
import id.com.wanderwisely.data.model.remote.`interface`.WeatherApiService
import id.com.wanderwisely.data.model.remote.`interface`.WisataApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        private val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
//            .addInterceptor { chain ->
//                val request = chain.request().newBuilder()
//                    .header("Content-Type", "application/json")
//                    .build()
//                chain.proceed(request)
//            }
            .build()

        private val retrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.API_URL_WISATA)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()

        private val rcmRetrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.API_URL_RECOMMEND)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()

        private val weatherRetrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.API_URL_WEATHER)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()

        fun getWisataApiService(): WisataApiService = retrofit.create(WisataApiService::class.java)
        fun getRecommendApiService(): RecommendApiService = rcmRetrofit.create(RecommendApiService::class.java)
        fun getWeatherApiService(): WeatherApiService = weatherRetrofit.create(WeatherApiService::class.java)
    }
}