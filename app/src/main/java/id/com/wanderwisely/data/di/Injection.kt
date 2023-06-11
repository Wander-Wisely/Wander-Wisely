package id.com.wanderwisely.data.di

import id.com.wanderwisely.data.repository.WanderWiselyRepository

object Injection {
    fun provideRepository(): WanderWiselyRepository {
        return WanderWiselyRepository.getInstance()
    }

}