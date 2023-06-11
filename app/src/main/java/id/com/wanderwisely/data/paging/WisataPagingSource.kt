package id.com.wanderwisely.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.com.wanderwisely.data.model.remote.`interface`.WisataApiService
import id.com.wanderwisely.data.model.response.WisataResponse

class WisataPagingSource(
    private val apiService: WisataApiService
): PagingSource<Int, WisataResponse>() {

    private companion object {
        const val PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, WisataResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WisataResponse> {
        return try{
            val position = params.key ?: PAGE_INDEX
            val response = apiService.getWisata(position, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        }catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}