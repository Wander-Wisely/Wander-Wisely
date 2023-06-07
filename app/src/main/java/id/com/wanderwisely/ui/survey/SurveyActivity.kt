package id.com.wanderwisely.ui.survey

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.com.wanderwisely.databinding.ActivitySurveyBinding
import id.com.wanderwisely.ui.home.HomeActivity

class SurveyActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurveyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun onCheckboxClicked(view: View) {}
    fun onRadioButtonClicked(view: View) {}
}