package id.com.wanderwisely.data.model.remote.`interface`

import id.com.wanderwisely.data.model.response.WisataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WisataApiService {
    @GET("alls")
    suspend fun getWisata(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): List<WisataResponse>

    @GET("alls/{id}")
    suspend fun getDetailWisata(
        @Path("id") id: Int?
    ): WisataResponse
}