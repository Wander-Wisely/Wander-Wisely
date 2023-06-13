package id.com.wanderwisely.ui.survey

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.*
import id.com.wanderwisely.data.model.remote.SurveyPref
import kotlinx.coroutines.launch


class SurveyViewModel(
    private val application: Application,
) : ViewModel() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "survey_preferences")

    private val _surveyData = MutableLiveData<SurveyPref>()
    val surveyData: LiveData<SurveyPref> = _surveyData

    fun submitSurveyData(hobbies: List<String>, types: List<String>, budgetMin: Int, budgetMax: Int) {
        val surveyPref = SurveyPref.getInstance(application.dataStore)
        viewModelScope.launch {
            surveyPref.saveSurveyPref(hobbies, types, budgetMin, budgetMax)
            _surveyData.value = surveyPref
        }
    }
}

class SurveyViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SurveyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SurveyViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}