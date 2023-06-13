package id.com.wanderwisely.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.com.wanderwisely.R
import id.com.wanderwisely.data.local.favorite.entity.FavoriteEntity
import id.com.wanderwisely.databinding.ActivityFavoriteBinding
import id.com.wanderwisely.ui.home.HomeActivity
import id.com.wanderwisely.ui.home.adapter.FavoriteAdapter
import id.com.wanderwisely.ui.plan.PlanActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter : FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.selectedItemId = R.id.menu_favorite
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true

                }
                R.id.menu_list_alt -> {
                    val intent = Intent(this, PlanActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true
                }
                R.id.menu_favorite -> {
                    true
                }
                else -> false
            }
        }
        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        adapter = FavoriteAdapter()
        adapter.notifyDataSetChanged()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
        favoriteViewModel.getFavoriteWisata()?.observe(this){
            if (it != null){
                val list = map(it)
                adapter.setList(list)
            }
        }
    }
    private fun map(wisatas : List<FavoriteEntity>) : ArrayList<FavoriteEntity>{
        val list = ArrayList<FavoriteEntity>()
        for(wisata in wisatas){
            val map = FavoriteEntity(
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