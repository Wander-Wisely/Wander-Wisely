package id.com.wanderwisely.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.com.wanderwisely.data.TouristPreferences
import id.com.wanderwisely.data.local.room.TouristRepository

class ViewModelFactory private constructor(
    private val application: Application,
    private val pref: TouristPreferences,
    private val repo: TouristRepository
    ): ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainVIewModel::class.java)){
            return MainVIewModel(application, pref, repo) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class: "+ modelClass.name)
    }
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(application: Application, pref: TouristPreferences, repo: TouristRepository): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(application, pref, repo)
            }.also { instance = it }
    }
}