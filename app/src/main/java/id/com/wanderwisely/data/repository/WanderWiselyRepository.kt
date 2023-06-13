package id.com.wanderwisely.data.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import id.com.wanderwisely.BuildConfig
import id.com.wanderwisely.data.model.remote.ApiConfig
import id.com.wanderwisely.data.model.remote.SurveyPref
import id.com.wanderwisely.data.model.response.DataItem
import id.com.wanderwisely.data.model.response.WeatherResponse
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.paging.WisataPagingSource
import kotlinx.coroutines.flow.first

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
    suspend fun getAllRecommend(context: Context, dataItems: MutableLiveData<List<DataItem>>){
        try {
            val surveyPreferences = surveyPref.surveyPreferencesFlow.first()
            val response = recommendApiService.surveyUser(surveyPreferences)
            dataItems.value = response.data as List<DataItem>?
        } catch (exception: Exception) {
            Toast.makeText(context, "${exception.message}",Toast.LENGTH_SHORT).show()
        }
    }
    suspend fun getDetailWisata(context: Context, touristId: Int, detailTourist: MutableLiveData<WisataResponse>){
        try {
            val response = ApiConfig.getWisataApiService().getDetailWisata(touristId)
            detailTourist.value = response
        }catch (t: Throwable){
            Toast.makeText(context, "${t.message}",Toast.LENGTH_SHORT).show()
        }
    }
    suspend fun getWeather(context: Context, lat: Double,lon: Double, weatherAll: MutableLiveData<List<WeatherResponse>>){
        try {
            val response = ApiConfig.getWeatherApiService().getWeather(lat,lon, BuildConfig.API_KEY_WEATHER)
            weatherAll.value = listOf(response)
        }catch (t: Throwable){
            Toast.makeText(context, "${t.message}",Toast.LENGTH_LONG).show()
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
