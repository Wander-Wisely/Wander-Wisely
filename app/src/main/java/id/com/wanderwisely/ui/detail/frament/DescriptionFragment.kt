package id.com.wanderwisely.ui.detail.frament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.SupportMapFragment
import id.com.wanderwisely.R
import id.com.wanderwisely.data.repository.Result
import id.com.wanderwisely.databinding.FragmentDescriptionBinding
import id.com.wanderwisely.ui.detail.DetailActivity


class DescriptionFragment : Fragment() {
    private var _binding : FragmentDescriptionBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: DescriptionFragmenViewModel
    private lateinit var name : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDescriptionBinding.bind(view)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DescriptionFragmenViewModel::class.java)
        viewModel.getDetailWisata(id)
        viewModel.getWisataDetail().observe(viewLifecycleOwner){
            if(it != null){
                binding?.description?.text = it.descriptions
                showLoading(false)
            }
        }

    }

    private fun showLoading(state: Boolean) {
        binding?.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }
}