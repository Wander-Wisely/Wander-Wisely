package id.com.wanderwisely.data.model.remote.`interface`

import id.com.wanderwisely.data.model.response.WisataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WisataApiService {
    @GET("alls")
    suspend fun getWisata(
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ) : List<WisataResponse>
}