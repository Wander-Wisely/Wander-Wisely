package id.com.wanderwisely.data.model.remote.`interface`

import id.com.wanderwisely.data.model.remote.SurveyRequestBody
import id.com.wanderwisely.data.model.response.RecommendResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RecommendApiService {
    @GET("index")
    suspend fun getRecommend(): RecommendResponse

    @POST("recommendation")
    suspend fun surveyUser(@Body body: SurveyRequestBody): RecommendResponse
}