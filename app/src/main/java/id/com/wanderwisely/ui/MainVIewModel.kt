package id.com.wanderwisely.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import id.com.wanderwisely.data.TouristPreferences
import id.com.wanderwisely.data.local.room.TouristRepository

class MainVIewModel(
    private val application: Application,
    private val pref: TouristPreferences,
    private val repo: TouristRepository
    ): ViewModel()
{

}