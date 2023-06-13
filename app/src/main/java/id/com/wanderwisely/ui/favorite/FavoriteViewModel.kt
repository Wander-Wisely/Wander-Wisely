package id.com.wanderwisely.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.com.wanderwisely.data.local.WanderWiselyDatabase
import id.com.wanderwisely.data.local.favorite.entity.FavoriteEntity
import id.com.wanderwisely.data.local.favorite.room.FavoriteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private var favoriteWisataDao : FavoriteDao?
    private var wanderWiselyDatabase : WanderWiselyDatabase?

    init{
        wanderWiselyDatabase = WanderWiselyDatabase.getDatabase(application)
        favoriteWisataDao = wanderWiselyDatabase?.favoriteDao()
    }
    fun getFavoriteWisata(): LiveData<List<FavoriteEntity>>?{
        return favoriteWisataDao?.getFavoriteWisata()
    }
    fun removeFavorite(id :Int){
        CoroutineScope(Dispatchers.IO).launch {
            favoriteWisataDao?.deleteAll(id)
        }
    }
}