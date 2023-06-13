package id.com.wanderwisely.ui.detail

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
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
import id.com.wanderwisely.ui.home.HomeActivity
import id.com.wanderwisely.ui.home.adapter.SectionsPagerAdapter
import id.com.wanderwisely.ui.plan.PlanActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var data: WisataResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailViewModelFactory = DetailViewModelFactory(this@DetailActivity.application)
        detailViewModel =
            ViewModelProvider(this, detailViewModelFactory)[DetailViewModel::class.java]

        val touristId = intent.getIntExtra("id", 0)
        detailViewModel.getDetailTourist(touristId)

        detailViewModel.detailTourist.observe(this){ id ->
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
        data = WisataResponse()
        val name = intent.getStringExtra("name")
        val city = intent.getStringExtra("city")
        val costTo = intent.getIntExtra("costto", 0)
        val costFrom = intent.getIntExtra("costfrom", 0)
        val tourismFile = intent.getStringExtra("tourismfile")

        binding.btnTambahRencana.setOnClickListener {
            detailViewModel.addPlan(city, costTo, name, touristId, tourismFile, costFrom)
            showDialog()
        }
        var _isCheckedFavorite = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailViewModel.checkFavorite(touristId)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.favorite.isChecked = true
                        _isCheckedFavorite = true
                    } else {
                        binding.favorite.isChecked = false
                        _isCheckedFavorite = false
                    }
                }
            }
        }
        binding.favorite.setOnClickListener {
            _isCheckedFavorite = !_isCheckedFavorite
            if (_isCheckedFavorite) {
                detailViewModel.addFavorite(city, costTo, name, touristId, tourismFile, costFrom)
                Toast.makeText(
                    this,
                    getString(R.string.successfully_added_favorite),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                detailViewModel.removeFavorite(touristId)
                Toast.makeText(
                    this,
                    getString(R.string.successfully_remove_favorite),
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.favorite.isChecked = _isCheckedFavorite
        }
        showLoading(false)
    }

    private fun setDetailTourist(data: WisataResponse) {
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

    private fun showDialog() {
        val dialogBinding = layoutInflater.inflate(R.layout.custom_dialog, null)
        val dialog = Dialog(this)
        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.show()
        val btnPlan: Button = dialog.findViewById(R.id.seePlan)
        val btnHome: Button = dialog.findViewById(R.id.toHome)
        btnPlan.setOnClickListener {
            val intent = Intent(this, PlanActivity::class.java)
            startActivity(intent)
        }
        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
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