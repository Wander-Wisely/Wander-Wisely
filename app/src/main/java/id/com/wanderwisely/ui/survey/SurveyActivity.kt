package id.com.wanderwisely.ui.survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.com.wanderwisely.R
import id.com.wanderwisely.databinding.ActivitySurveyBinding
import id.com.wanderwisely.ui.detail.DetailActivity

class SurveyActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurveyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }
}