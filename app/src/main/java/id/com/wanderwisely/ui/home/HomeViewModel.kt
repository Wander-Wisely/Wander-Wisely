package id.com.wanderwisely.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.com.wanderwisely.data.di.Injection
import id.com.wanderwisely.data.model.response.DataItem
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.repository.WanderWiselyRepository
import id.com.wanderwisely.ui.survey.SurveyViewModel

class HomeViewModel(wanderWiselyRepository: WanderWiselyRepository, surveyViewModel: SurveyViewModel):ViewModel() {
    val wisata : LiveData<PagingData<WisataResponse>> = wanderWiselyRepository.getAllWisata().cachedIn(viewModelScope)

    private val _recommendation = MutableLiveData<List<DataItem?>?>()
    val recommendation: LiveData<List<DataItem?>?> = _recommendation

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        surveyViewModel.recommendResponse.observeForever { response ->
            if (response != null) {
                val recommendation = response.data
                _recommendation.value = recommendation
            }
        }
    }
}
class HomeViewModelFactory(private val surveyViewModel: SurveyViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(Injection.provideRepository(), surveyViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}