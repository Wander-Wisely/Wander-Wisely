package id.com.wanderwisely.ui.plan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.com.wanderwisely.R
import id.com.wanderwisely.databinding.ActivityPlanBinding
import id.com.wanderwisely.ui.favorite.FavoriteActivity
import id.com.wanderwisely.ui.home.HomeActivity

class PlanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlanBinding
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

    }
}