package id.com.wanderwisely.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.com.wanderwisely.data.local.favorite.entity.FavoriteEntity
import id.com.wanderwisely.data.local.favorite.room.FavoriteDao
import id.com.wanderwisely.data.local.plan.entity.PlanEntity
import id.com.wanderwisely.data.local.plan.room.PlanWisataDao

@Database(entities = [PlanEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class WanderWiselyDatabase : RoomDatabase() {

    abstract fun planWisataDao(): PlanWisataDao
    abstract fun favoriteDao(): FavoriteDao
    companion object{
        var INSTANCE : WanderWiselyDatabase? = null

        fun getDatabase(context: Context):WanderWiselyDatabase? {
            if(INSTANCE == null){
                synchronized(WanderWiselyDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WanderWiselyDatabase::class.java, "WanderWisly_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}