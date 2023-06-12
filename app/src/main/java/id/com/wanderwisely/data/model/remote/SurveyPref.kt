package id.com.wanderwisely.data.model.remote

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

class SurveyPref private constructor(private val dataStore: DataStore<Preferences>) {
    private val hobbies = stringPreferencesKey("hobbies")
    private val types = stringPreferencesKey("types")
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
                    hobbies = preferences[hobbies]?.split(","),
                    types = preferences[types]?.split(","),
                    budgetMin = preferences[budgetMin],
                    budgetMax = preferences[budgetMax]
                )
            }

    suspend fun saveSurveyPref(hobbies: List<String>, types: List<String>, budgetMin: Int, budgetMax: Int) {
        dataStore.edit { preferences ->
            preferences[this.hobbies] = hobbies.joinToString(",")
            preferences[this.types] = types.joinToString(",")
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
    val budgetMin: Int?,
    val budgetMax: Int?
)