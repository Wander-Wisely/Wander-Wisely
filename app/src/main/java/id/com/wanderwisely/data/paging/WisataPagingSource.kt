package id.com.wanderwisely.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.com.wanderwisely.data.model.remote.ApiService
import id.com.wanderwisely.data.model.response.WisataResponse

class WisataPagingSource(
    private val apiService: ApiService
): PagingSource<Int, WisataResponse>() {
    private val PAGE_INDEX = 1
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WisataResponse> {
        return try{
            val position = params.key ?: PAGE_INDEX
            val response = apiService.getWisata()

            LoadResult.Page(
                data = response,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = position.plus(1)
            )
        }catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, WisataResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}