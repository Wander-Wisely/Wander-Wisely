package id.com.wanderwisely.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import id.com.wanderwisely.data.model.remote.ApiService
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.paging.WisataPagingSource

class WanderWiselyRepository private constructor(
    private val apiService: ApiService
) {
    fun getAllWisata(): LiveData<PagingData<WisataResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { WisataPagingSource(apiService) }
        ).liveData
    }
    companion object{
        @Volatile
        private var INSTANCE : WanderWiselyRepository?= null
        fun getInstance(apiService: ApiService)=
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: WanderWiselyRepository(apiService)
            }.also { INSTANCE = it }
    }
}
