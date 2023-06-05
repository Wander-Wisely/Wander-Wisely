package id.com.wanderwisely.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.com.wanderwisely.data.di.Injection
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.repository.WanderWiselyRepository

class HomeViewModel(wanderWiselyRepository: WanderWiselyRepository):ViewModel() {
    val wisata : LiveData<PagingData<WisataResponse>> = wanderWiselyRepository.getAllWisata().cachedIn(viewModelScope)
}
class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(Injection.provideRepository(), ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}