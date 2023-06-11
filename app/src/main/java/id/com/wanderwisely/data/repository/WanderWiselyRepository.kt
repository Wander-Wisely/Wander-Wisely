package id.com.wanderwisely.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import id.com.wanderwisely.data.model.remote.ApiConfig
import id.com.wanderwisely.data.model.remote.SurveyRequestBody
import id.com.wanderwisely.data.model.response.DataItem
import id.com.wanderwisely.data.model.response.RecommendResponse
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.paging.RecommendPagingSource
import id.com.wanderwisely.data.paging.WisataPagingSource

class WanderWiselyRepository private constructor(

) {
    private var wisataApiService = ApiConfig.getWisataApiService()
    private var recommendApiService = ApiConfig.getRecommendApiService()
    fun getAllWisata(): LiveData<PagingData<WisataResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { WisataPagingSource(wisataApiService) }
        ).liveData
    }
    fun getAllRecommend(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { RecommendPagingSource(recommendApiService) }
        ).liveData
    }
    suspend fun surveyUser(
        hobbies: List<String>,
        types: List<String>,
        budgetMin: Int,
        budgetMax: Int
    ): RecommendResponse {
        val requestBody = SurveyRequestBody(hobbies, types, budgetMin, budgetMax)
        return recommendApiService.surveyUser(requestBody)
    }
    companion object{
        @Volatile
        private var INSTANCE : WanderWiselyRepository?= null
        fun getInstance()=
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: WanderWiselyRepository()
            }.also { INSTANCE = it }
    }
}
