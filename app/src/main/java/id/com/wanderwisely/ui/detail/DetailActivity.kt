package id.com.wanderwisely.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.com.wanderwisely.R
import id.com.wanderwisely.data.model.response.WeatherResponse
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.databinding.ActivityDetailBinding
import id.com.wanderwisely.ui.home.adapter.SectionsPagerAdapter

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailViewModelFactory = DetailViewModelFactory(this@DetailActivity.application)
        detailViewModel = ViewModelProvider(this, detailViewModelFactory)[DetailViewModel::class.java]

        val touristId = intent.getIntExtra("id", 0)
        detailViewModel.getDetailTourist(touristId)

        val cityName = intent.getStringExtra("city")
        if (cityName != null) {
            detailViewModel.getWeather(cityName)
        }

        detailViewModel.detailTourist.observe(this){id ->
            setDetailTourist(id)
            supportActionBar?.title = id.name
        }

        detailViewModel.weatherAll.observe(this){city->
            setWeather(city)
        }

        //section adapter
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        binding.btnTambahRencana.setOnClickListener {
//            insertPlanToDatabase()
//        }
        showLoading(false)
    }

    private fun setDetailTourist(data: WisataResponse){
        binding.rating.text = data.rating
        val imgFiles = data.tourismFiles?.firstOrNull()
        if (imgFiles != null) {
            Glide.with(this)
                .load(imgFiles.path)
                .into(binding.imageView)
        }
    }

    private fun setWeather(data: WeatherResponse){

    }
    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE

    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}