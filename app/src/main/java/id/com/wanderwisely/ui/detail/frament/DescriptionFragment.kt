package id.com.wanderwisely.ui.detail.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.databinding.FragmentDescriptionBinding
import id.com.wanderwisely.ui.detail.DetailViewModel


class DescriptionFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        detailViewModel.detailTourist.observe(viewLifecycleOwner){tourist->
            setDescription(tourist)
            if (tourist != null){
                showLoading(false)
            }else{
                showLoading(true)
            }
        }
    }

    fun setDescription(touristId: WisataResponse){
        binding.description.text = touristId.descriptions
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}