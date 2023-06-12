package id.com.wanderwisely.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.com.wanderwisely.data.model.remote.SurveyPref
import id.com.wanderwisely.data.model.remote.`interface`.RecommendApiService
import id.com.wanderwisely.data.model.response.DataItem
import kotlinx.coroutines.flow.first

class RecommendPagingSource(
    private val apiService: RecommendApiService,
    private val surveyPref: SurveyPref
) : PagingSource<Int, DataItem>() {
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
        return try {
            val position = params.key ?: PAGE_INDEX
            val surveyPreferences = surveyPref.surveyPreferencesFlow.first()
            val response = apiService.surveyUser(surveyPreferences)
            val dataItems = response.data?.filterNotNull() ?: emptyList()

            LoadResult.Page(
                data = dataItems,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (dataItems.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}