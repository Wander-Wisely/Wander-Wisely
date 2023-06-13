package id.com.wanderwisely.data.model.remote.`interface`

import id.com.wanderwisely.data.model.remote.SurveyPreferences
import id.com.wanderwisely.data.model.response.RecommendResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RecommendApiService {
    @Headers("Content-Type: application/json")
    @POST("recommendation")
    suspend fun surveyUser(@Body body: SurveyPreferences): RecommendResponse
}