package id.com.wanderwisely.ui.detail

import android.app.Application
import androidx.lifecycle.*
import id.com.wanderwisely.data.di.Injection
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.repository.WanderWiselyRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val wanderWiselyRepository: WanderWiselyRepository, private val application: Application): ViewModel() {

    private val _detailTourist = MutableLiveData<WisataResponse>()
    val detailTourist: LiveData<WisataResponse> = _detailTourist

    fun getDetailTourist(touristId: Int){
        viewModelScope.launch {
            wanderWiselyRepository.getDetailWisata(application, touristId, _detailTourist)
        }
    }
}

class DetailViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(Injection.provideRepository(application), application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}