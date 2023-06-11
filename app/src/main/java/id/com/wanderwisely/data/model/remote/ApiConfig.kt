package id.com.wanderwisely.data.model.remote

import id.com.wanderwisely.BuildConfig
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
            .build()

        private val retrofit = Retrofit.Builder().apply {
            baseUrl("https://backend-dot-wanderwiselyc23ps444.et.r.appspot.com/api/v1/")
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()

        private val rcmRetrofit = Retrofit.Builder().apply {
            baseUrl("https://9f62-180-248-47-144.ngrok-free.app/")
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()

        fun getWisataApiService(): ApiService = retrofit.create(ApiService::class.java)
        fun getRecommendApiServise(): ApiService = retrofit.create(ApiService::class.java)
    }
}