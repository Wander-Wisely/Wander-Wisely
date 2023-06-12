package id.com.wanderwisely.data.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import id.com.wanderwisely.data.model.remote.ApiConfig
import id.com.wanderwisely.data.model.remote.SurveyPref
import id.com.wanderwisely.data.model.response.DataItem
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.paging.RecommendPagingSource
import id.com.wanderwisely.data.paging.WisataPagingSource

class WanderWiselyRepository(
    private val surveyPref: SurveyPref
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
            pagingSourceFactory = { RecommendPagingSource(recommendApiService,surveyPref) }
        ).liveData
    }
    suspend fun getDetailWisata(context: Context, touristId: Int, detailTourist: MutableLiveData<WisataResponse>){
        try {
            val response = ApiConfig.getWisataApiService().getDetailWisata(touristId)
            detailTourist.value = response
        }catch (t: Throwable){
            Toast.makeText(context, "${t.message}",Toast.LENGTH_SHORT).show()
        }
    }
    companion object{
        @Volatile
        private var INSTANCE : WanderWiselyRepository?= null
        fun getInstance(surveyPref: SurveyPref)=
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: WanderWiselyRepository(surveyPref)
            }.also { INSTANCE = it }
    }
}
