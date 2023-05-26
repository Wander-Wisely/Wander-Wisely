package id.com.wanderwisely.data

import androidx.datastore.core.DataStore
import java.util.prefs.Preferences

class TouristPreferences private constructor(private val dataStore: DataStore<Preferences>) {
}