package id.com.wanderwisely.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.com.wanderwisely.R
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.databinding.ActivityDetailBinding
import id.com.wanderwisely.ui.home.adapter.SectionsPagerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var data : WisataResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailViewModelFactory = DetailViewModelFactory(this@DetailActivity.application)
        detailViewModel = ViewModelProvider(this, detailViewModelFactory)[DetailViewModel::class.java]

        val touristId = intent.getIntExtra("id", 0)
        detailViewModel.getDetailTourist(touristId)

        detailViewModel.detailTourist.observe(this){id ->
            setDetailTourist(id)
            supportActionBar?.title = id.name
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
        data = WisataResponse()
        binding.btnTambahRencana.setOnClickListener {
            val name = intent.getStringExtra("name")
            val city = intent.getStringExtra("city")
            val costTo = intent.getStringExtra("costto")?.toInt()
            val costFrom = intent.getStringExtra("costfrom")?.toInt()
            val tourismFile = intent.getStringExtra("tourismfile")
            detailViewModel.addPlan(city, costTo ,name, touristId, tourismFile,costFrom)
            Toast.makeText(this, "Successfully Added Plan", Toast.LENGTH_SHORT).show()
        }
        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailViewModel.checkFavorite(touristId)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.favorite.isChecked = true
                        _isChecked = true
                    } else {
                        binding.favorite.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }
        binding.favorite.setOnClickListener {
            val name = intent.getStringExtra("name")
            val city = intent.getStringExtra("city")
            val costTo = intent.getStringExtra("costto")?.toInt()
            val costFrom = intent.getStringExtra("costfrom")?.toInt()
            val tourismFile = intent.getStringExtra("tourismfile")
            _isChecked = !_isChecked
            if (_isChecked){
                detailViewModel.addFavorite(city, costTo ,name, touristId, tourismFile,costFrom)
                Toast.makeText(this, "Successfully Added To Favorite", Toast.LENGTH_SHORT).show()
            }else{
                detailViewModel.removeFavorite(touristId)
                Toast.makeText(this, "Successfully Delete To Favorite", Toast.LENGTH_SHORT).show()
            }
        }
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