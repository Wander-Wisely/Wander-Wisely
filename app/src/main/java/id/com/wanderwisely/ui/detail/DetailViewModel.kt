package id.com.wanderwisely.ui.detail

import android.app.Application
import androidx.lifecycle.*
import id.com.wanderwisely.data.di.Injection
import id.com.wanderwisely.data.model.response.WeatherResponse
import id.com.wanderwisely.data.local.WanderWiselyDatabase
import id.com.wanderwisely.data.local.favorite.entity.FavoriteEntity
import id.com.wanderwisely.data.local.favorite.room.FavoriteDao
import id.com.wanderwisely.data.local.plan.entity.PlanEntity
import id.com.wanderwisely.data.local.plan.room.PlanWisataDao
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.repository.WanderWiselyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val wanderWiselyRepository: WanderWiselyRepository, private val application: Application): ViewModel() {

    private val _detailTourist = MutableLiveData<WisataResponse>()
    val detailTourist: LiveData<WisataResponse> = _detailTourist

    private var planWisataDao : PlanWisataDao?
    private var favoriteWisataDao : FavoriteDao?
    private var wanderWiselyDatabase : WanderWiselyDatabase? = WanderWiselyDatabase.getDatabase(application)

    private val _weatherAll = MutableLiveData<List<WeatherResponse>>()
    val weatherAll: LiveData<List<WeatherResponse>> = _weatherAll

    init{
        planWisataDao = wanderWiselyDatabase?.planWisataDao()
        favoriteWisataDao = wanderWiselyDatabase?.favoriteDao()
    }
    fun addPlan(city: String? = null, costTo: Int, name: String? = null, id: Int?, tourismFiles: String?, costFrom: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val wisata = PlanEntity(
                id,
                name,
                city,
                costFrom,
                costTo,
                tourismFiles
            )
            planWisataDao?.addToPlan(wisata)
        }
    }
    fun addFavorite(city: String? = null, costTo: Int, name: String? = null, id: Int?, tourismFiles: String?, costFrom: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val wisata = FavoriteEntity(
                id,
                name,
                city,
                costFrom,
                costTo,
                tourismFiles
            )
            favoriteWisataDao?.addToFavorite(wisata)
        }
    }
    fun checkFavorite(id: Int) = favoriteWisataDao?.isFavoriteAdd(id)
    fun removeFavorite(id :Int){
        CoroutineScope(Dispatchers.IO).launch {
            favoriteWisataDao?.deleteAll(id)
        }
    }
    fun getDetailTourist(touristId: Int){
        viewModelScope.launch {
            wanderWiselyRepository.getDetailWisata(application, touristId, _detailTourist)
        }
    }
    fun getWeather(lat: Double, lon: Double){
        viewModelScope.launch {
            wanderWiselyRepository.getWeather(application, lat, lon, _weatherAll)
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