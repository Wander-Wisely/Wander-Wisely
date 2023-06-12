package id.com.wanderwisely.ui.home

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.com.wanderwisely.data.di.Injection
import id.com.wanderwisely.data.model.response.DataItem
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.repository.WanderWiselyRepository

class HomeViewModel(
    wanderWiselyRepository: WanderWiselyRepository,
):ViewModel() {
    val wisata : LiveData<PagingData<WisataResponse>> = wanderWiselyRepository.getAllWisata().cachedIn(viewModelScope)
    val recommendation: LiveData<PagingData<DataItem>> = wanderWiselyRepository.getAllRecommend().cachedIn(viewModelScope)

}
class HomeViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(Injection.provideRepository(application)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}