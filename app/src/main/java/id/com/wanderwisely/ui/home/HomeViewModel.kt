package id.com.wanderwisely.ui.home

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.com.wanderwisely.data.di.Injection
import id.com.wanderwisely.data.model.response.DataItem
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.repository.WanderWiselyRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val wanderWiselyRepository: WanderWiselyRepository,
    private val application: Application
):ViewModel() {
    val wisata : LiveData<PagingData<WisataResponse>> = wanderWiselyRepository.getAllWisata().cachedIn(viewModelScope)
    private val _recommendation = MutableLiveData<List<DataItem>>()
    val recommendation: LiveData<List<DataItem>> = _recommendation

    init {
        getRecommend()
    }
    private fun getRecommend(){
        viewModelScope.launch {
            wanderWiselyRepository.getAllRecommend(application, _recommendation)
        }
    }
//    val recommendation: LiveData<PagingData<DataItem>> = wanderWiselyRepository.getAllRecommend().cachedIn(viewModelScope)
}
class HomeViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(Injection.provideRepository(application),application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}