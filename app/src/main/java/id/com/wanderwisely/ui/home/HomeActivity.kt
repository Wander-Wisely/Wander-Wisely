package id.com.wanderwisely.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.com.wanderwisely.R
import id.com.wanderwisely.data.model.response.DataItem
import id.com.wanderwisely.databinding.ActivityHomeBinding
import id.com.wanderwisely.ui.home.adapter.WisataAdapter
import id.com.wanderwisely.ui.favorite.FavoriteActivity
import id.com.wanderwisely.ui.home.adapter.LoadingStateAdapter
import id.com.wanderwisely.ui.plan.PlanActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var homeViewModel :HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeViewModelFactory = HomeViewModelFactory()
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]

        binding.bottomNavigationView.selectedItemId = R.id.menu_home
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    true
                }
                R.id.menu_list_alt -> {
                    val intent = Intent(this, PlanActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
        setupAction()

        homeViewModel.recommendation.observe(this) { recommendation ->
            if (recommendation != null) {
                handleRecommendation(recommendation)
            }
        }
    }
    private fun setupAction(){
        val adapter = WisataAdapter()
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        homeViewModel.wisata.observe(this){
            adapter.submitData(lifecycle,it)
        }
    }
    private fun handleRecommendation(recommendation: List<DataItem?>?) {

    }
}