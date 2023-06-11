package id.com.wanderwisely.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.com.wanderwisely.data.model.remote.ApiService
import id.com.wanderwisely.data.model.remote.`interface`.RecommendApiService
import id.com.wanderwisely.data.model.response.DataItem

class RecommendPagingSource(
    private val apiService: RecommendApiService
): PagingSource<Int, DataItem>()
{
    private companion object {
        const val PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try{
            val position = params.key ?: PAGE_INDEX
            val response = apiService.getRecommend().data

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