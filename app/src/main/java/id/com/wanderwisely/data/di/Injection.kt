package id.com.wanderwisely.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import id.com.wanderwisely.data.model.remote.SurveyPref
import id.com.wanderwisely.data.repository.WanderWiselyRepository


object Injection {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "survey_preferences")
    private fun provideDataStore(context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    fun provideSurveyPref(context: Context): SurveyPref {
        val dataStore = provideDataStore(context)
        return SurveyPref.getInstance(dataStore)
    }
    fun provideRepository(context: Context): WanderWiselyRepository {
        val surveyPref = provideSurveyPref(context)
        return WanderWiselyRepository.getInstance(surveyPref)
    }
}