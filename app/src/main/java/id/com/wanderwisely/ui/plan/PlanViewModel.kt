package id.com.wanderwisely.ui.plan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.com.wanderwisely.data.local.WanderWiselyDatabase
import id.com.wanderwisely.data.local.plan.entity.PlanEntity
import id.com.wanderwisely.data.local.plan.room.PlanWisataDao

class PlanViewModel(application: Application): AndroidViewModel(application) {
    private var planWisataDao : PlanWisataDao?
    private var wanderWiselyDatabase : WanderWiselyDatabase?

    init{
        wanderWiselyDatabase = WanderWiselyDatabase.getDatabase(application)
        planWisataDao = wanderWiselyDatabase?.planWisataDao()
    }
    fun getPlanWisata(): LiveData<List<PlanEntity>>?{
        return planWisataDao?.getPlanWisata()
    }
}