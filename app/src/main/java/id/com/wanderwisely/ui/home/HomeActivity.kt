package id.com.wanderwisely.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.com.wanderwisely.R
import id.com.wanderwisely.databinding.ActivityHomeBinding
import id.com.wanderwisely.ui.favorite.FavoriteActivity
import id.com.wanderwisely.ui.home.adapter.LoadingStateAdapter
import id.com.wanderwisely.ui.home.adapter.RecommendAdapter
import id.com.wanderwisely.ui.home.adapter.WisataAdapter
import id.com.wanderwisely.ui.plan.PlanActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var homeViewModel :HomeViewModel
    private lateinit var wisataAdapter: WisataAdapter
    private lateinit var recommendAdapter: RecommendAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeViewModelFactory = HomeViewModelFactory(this@HomeActivity.application)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]

        wisataAdapter = WisataAdapter()
        recommendAdapter = RecommendAdapter()

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
        handleRecommendation()
        binding.allCategory.setOnClickListener {
            filterData(null, null)
        }

        binding.BeachCategory.setOnClickListener {
            filterData(null, "Pantai")
        }

        binding.NationalParkCategory.setOnClickListener {
            filterData(null, "Taman Nasional")
        }

        binding.NatureCategory.setOnClickListener {
            filterData(null, "Alam")
        }

        binding.CategoryHistory.setOnClickListener {
            filterData(null, "Sejarah")
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as? SearchView

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.queryHint = resources.getString(R.string.search_hint)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterData(query,null)
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                filterData(newText,null)
                return true
            }
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setupAction(){
        binding.recyclerView.adapter = wisataAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                wisataAdapter.retry()
            })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        homeViewModel.wisata.observe(this) {data ->
            wisataAdapter.updateData(data)
            filterData(null,null)
        }
    }
    private fun handleRecommendation() {
        binding.rvRecommendation.adapter = recommendAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                recommendAdapter.retry()
            }
        )
        binding.rvRecommendation.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        homeViewModel.recommendation.observe(this) { data ->
            recommendAdapter.submitData(lifecycle, data)
        }
    }

    private fun filterData(query: String?, category: String?) {
        CoroutineScope(Dispatchers.Main).launch {
            wisataAdapter.filterData(query,category)
        }
    }
}