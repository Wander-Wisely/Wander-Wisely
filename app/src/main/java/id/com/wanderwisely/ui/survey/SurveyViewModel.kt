package id.com.wanderwisely.ui.survey

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import id.com.wanderwisely.data.di.Injection
import id.com.wanderwisely.data.model.response.RecommendResponse
import id.com.wanderwisely.data.repository.WanderWiselyRepository
import kotlinx.coroutines.launch

class SurveyViewModel(
    private val application: Application,
    private val wanderWiselyRepository: WanderWiselyRepository
): ViewModel() {
    private val _recommendResponse = MutableLiveData<RecommendResponse>()
    val recommendResponse: LiveData<RecommendResponse> = _recommendResponse

    fun surveyUser(hobbies: List<String>, types: List<String>, budgetMin: Int, budgetMax: Int) {
        viewModelScope.launch {

            try {
                val response = wanderWiselyRepository.surveyUser(hobbies, types, budgetMin, budgetMax)
                _recommendResponse.value = response
            } catch (e: Exception) {
                val errorMessage = "An error occurred: ${e.message}"
                Toast.makeText(application, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class SurveyViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SurveyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SurveyViewModel(application, Injection.provideRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}