package id.com.wanderwisely.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.com.wanderwisely.R
import id.com.wanderwisely.databinding.ActivityDetailBinding
import id.com.wanderwisely.ui.home.adapter.SectionsPagerAdapter

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //section adapter
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabs, viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        val image = "https://drive.google.com/file/d/1lzYeoVZGaf-G-VsKLwZugkeUKxYlaG9Q/view?usp=share_link"
        Glide.with(this)
            .load(image)
            .into(binding.imageView)

        binding.btnTambahRencana.setOnClickListener {
//            insertPlanToDatabase()
        }
        setupView()
    }
    private fun setupView(){
        val name = intent.getStringExtra(NAME_EXTRA)
        val city = intent.getStringExtra(CITY_EXTRA)
        val cost = intent.getStringExtra(COST_EXTRA)
        val path = intent.getStringExtra(PATH_EXTRA)
        val rating = intent.getStringExtra(RATING_EXTRA)

        supportActionBar?.title = name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.rating.text = rating
        Glide.with(this)
            .load(path)
            .into(binding.imageView)
    }
    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        const val NAME_EXTRA = "name_extra"
        const val CITY_EXTRA = "city_extra"
        const val COST_EXTRA = "cost_extra"
        const val PATH_EXTRA = "path_extra"
        const val RATING_EXTRA = "rating_extra"
    }
}