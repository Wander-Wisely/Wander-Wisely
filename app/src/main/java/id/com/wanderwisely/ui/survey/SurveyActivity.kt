package id.com.wanderwisely.ui.survey

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.com.wanderwisely.R
import id.com.wanderwisely.databinding.ActivitySurveyBinding
import id.com.wanderwisely.ui.home.HomeActivity

class SurveyActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurveyBinding
    private lateinit var surveyViewModel: SurveyViewModel
    private val selectedHobbies = mutableListOf<String>()
    private val selectedTypes = mutableListOf<String>()
    private var budgetMin: Int = 0
    private var budgetMax: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val surveyViewModelFactory = SurveyViewModelFactory(this@SurveyActivity.application)
        surveyViewModel = ViewModelProvider(this, surveyViewModelFactory)[SurveyViewModel::class.java]

        binding.btnContinue.setOnClickListener {
            val hobbies = selectedHobbies.toList()
            val types = selectedTypes.toList()
            val budgetMin = budgetMin
            val budgetMax = budgetMax

            surveyViewModel.surveyUser(hobbies, types, budgetMin, budgetMax)

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun onCheckboxClicked(view: View){
        if (view is CheckBox) {
            val checked = view.isChecked
            // Check the selected checkboxes and add their values to the respective lists
            when (view.id) {
                R.id.Hiking, R.id.Snorkeling, R.id.Photography, R.id.Trekking, R.id.Outbound, R.id.Culinary -> {
                    if (checked) selectedHobbies.add(view.text.toString())
                }
                R.id.Popular, R.id.Quiet, R.id.Lodge, R.id.Camp, R.id.Family, R.id.Alone, R.id.National_Park, R.id.Beach, R.id.Nature, R.id.History -> {
                    if (checked) selectedTypes.add(view.text.toString())
                }
            }
        }
    }
    fun onRadioButtonClicked(view: View){
        if (view is RadioButton) {
            val isChecked = view.isChecked
            when (view.id) {
                R.id.type1 -> if (isChecked) {
                    budgetMax = 50000
                }
                R.id.type2 -> if (isChecked) {
                    budgetMin = 50000
                    budgetMax = 100000
                }
                R.id.type3 -> if (isChecked) {
                    budgetMin = 100000
                    budgetMax = 250000
                }
                R.id.type4 -> if (isChecked) {
                    budgetMin = 250000
                }
            }
        }
    }
}