package id.com.wanderwisely.data.di

import android.content.Context
import id.com.wanderwisely.data.model.remote.ApiConfigWisata

import id.com.wanderwisely.data.repository.WanderWiselyRepository

object Injection {
    fun provideRepository(): WanderWiselyRepository {
        val apiService = ApiConfigWisata.getApiService()
        return WanderWiselyRepository.getInstance(apiService)
    }
}