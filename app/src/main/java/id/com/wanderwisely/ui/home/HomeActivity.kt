package id.com.wanderwisely.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.com.wanderwisely.databinding.ActivityHomeBinding
import id.com.wanderwisely.ui.detail.adapter.WisataAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var homeViewModel :HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeViewModelFactory = HomeViewModelFactory(this)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
        setupAction()
    }
    private fun setupAction(){
        val adapter = WisataAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        homeViewModel.wisata.observe(this){
            adapter.submitData(lifecycle,it)
        }
    }
}