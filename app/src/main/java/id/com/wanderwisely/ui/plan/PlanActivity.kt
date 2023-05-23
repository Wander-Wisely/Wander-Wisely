package id.com.wanderwisely.ui.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.com.wanderwisely.databinding.ActivityPlanBinding

class PlanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}