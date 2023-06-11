package id.com.wanderwisely.data.di

import id.com.wanderwisely.data.model.remote.ApiConfig
import id.com.wanderwisely.data.repository.WanderWiselyRepository

object Injection {
    private val apiServiceWisata = ApiConfig.getWisataApiService()
    private val apiServiceRekomendasi = ApiConfig.getRecommendApiServise()

    fun provideRepository(): WanderWiselyRepository {
        return WanderWiselyRepository.getInstance(apiServiceWisata)
    }

    fun provideRecommandation(): WanderWiselyRepository {
        return WanderWiselyRepository.getInstance(apiServiceRekomendasi)
    }

}