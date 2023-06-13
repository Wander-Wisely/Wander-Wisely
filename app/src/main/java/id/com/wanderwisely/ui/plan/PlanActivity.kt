package id.com.wanderwisely.ui.plan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.com.wanderwisely.R
import id.com.wanderwisely.data.local.plan.entity.PlanEntity
import id.com.wanderwisely.databinding.ActivityPlanBinding
import id.com.wanderwisely.ui.favorite.FavoriteActivity
import id.com.wanderwisely.ui.home.HomeActivity
import id.com.wanderwisely.ui.home.adapter.PlanAdapter

class PlanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlanBinding
    private lateinit var planViewModel: PlanViewModel
    private lateinit var adapter : PlanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.selectedItemId = R.id.menu_list_alt
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true

                }
                R.id.menu_list_alt -> {
                    true
                }
                R.id.menu_favorite -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true
                }
                else -> false
            }
        }
        planViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)
        adapter = PlanAdapter()
        adapter.notifyDataSetChanged()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@PlanActivity)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
        planViewModel.getPlanWisata()?.observe(this){
            if (it != null){
                val list = map(it)
                adapter.setList(list)
            }
        }
        adapter.setOnFavoriteClickListener { plan ->
            planViewModel.removePlan(plan.id ?: 0)
        }
    }
    private fun map(wisatas : List<PlanEntity>) : ArrayList<PlanEntity>{
        val list = ArrayList<PlanEntity>()
        for(wisata in wisatas){
            val map = PlanEntity(
                wisata.id,
                wisata.name,
                wisata.city,
                wisata.costFrom,
                wisata.costTo,
                wisata.path
            )
            list.add(map)
        }
        return list
    }
}