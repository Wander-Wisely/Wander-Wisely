package id.com.wanderwisely.data.model.remote

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

class SurveyPref private constructor(private val dataStore: DataStore<Preferences>) {
    private val hobbies = stringSetPreferencesKey("hobbies")
    private val types = stringSetPreferencesKey("types")
    private val budgetMin = intPreferencesKey("budget_min")
    private val budgetMax = intPreferencesKey("budget_max")

    val surveyPreferencesFlow: Flow<SurveyPreferences>
        get() = dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                SurveyPreferences(
                    hobbies = preferences[hobbies]?.toList() ?: emptyList(),
                    types = preferences[types]?.toList() ?: emptyList(),
                    budget_min = preferences[budgetMin],
                    budget_max = preferences[budgetMax]
                )
            }

    suspend fun saveSurveyPref(hobbies: List<String>, types: List<String>, budgetMin: Int, budgetMax: Int) {
        dataStore.edit { preferences ->
            preferences[this.hobbies] = hobbies.toSet()
            preferences[this.types] = types.toSet()
            preferences[this.budgetMin] = budgetMin
            preferences[this.budgetMax] = budgetMax
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: SurveyPref? = null

        fun getInstance(dataStore: DataStore<Preferences>): SurveyPref {
            return INSTANCE ?: synchronized(this) {
                val instance = SurveyPref(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}

data class SurveyPreferences(
    val hobbies: List<String>?,
    val types: List<String>?,
    val budget_min: Int?,
    val budget_max: Int?
)