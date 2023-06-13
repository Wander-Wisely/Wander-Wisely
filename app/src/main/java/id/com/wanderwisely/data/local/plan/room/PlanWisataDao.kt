package id.com.wanderwisely.data.local.plan.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.com.wanderwisely.data.local.plan.entity.PlanEntity

@Dao
interface PlanWisataDao {
    @Query("SELECT * FROM plan_wisata ORDER BY id ASC")
    fun getPlanWisata(): LiveData<List<PlanEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToPlan(favoriteEntity: PlanEntity)

    @Update
    fun updatePlan(favoriteEntity: PlanEntity)

    @Query("DELETE FROM plan_wisata WHERE id = :id")
    fun deleteAll(id :Int) : Int

    @Query("SELECT EXISTS(SELECT * FROM plan_wisata WHERE plan_wisata.id = :id)")
    fun isPlanAdd(id : Int) : Boolean
}