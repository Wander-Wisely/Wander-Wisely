package id.com.wanderwisely.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import id.com.wanderwisely.R
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.com.wanderwisely.databinding.ActivityHomeBinding
import id.com.wanderwisely.ui.detail.adapter.WisataAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private val homeViewModel :HomeViewModel by viewModels{
        HomeViewModelFactory(this)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
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