package id.com.wanderwisely.data.local.favorite.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.com.wanderwisely.data.local.favorite.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getFavoriteWisata(): LiveData<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorite(favoriteEntity: FavoriteEntity)

    @Update
    fun updateFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE id = :id")
    fun deleteAll(id : Int) : Int

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE favorite.id = :id)")
    fun isFavoriteAdd(id : Int) : Int
}