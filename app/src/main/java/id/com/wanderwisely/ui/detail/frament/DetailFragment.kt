package id.com.wanderwisely.ui.detail.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import id.com.wanderwisely.R
import id.com.wanderwisely.data.model.response.TourismFacilitiesItem
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.databinding.FragmentDetailBinding
import id.com.wanderwisely.ui.detail.DetailViewModel

class DetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    private val callback = OnMapReadyCallback { googleMap ->
        val location = LatLng(-6.3538282
            , 127.940833)
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("Gunung Rinjani")
                .snippet("Gunung Rinjani Jawa timur")
        )
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]

        detailViewModel.detailTourist.observe(viewLifecycleOwner){tourist->
            setDetailFragment(tourist)
        }
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }
    fun setDetailFragment(touristId: WisataResponse){
        val tourismFacilities: List<TourismFacilitiesItem?>? = touristId.tourismFacilities
        val formattedText = tourismFacilities?.joinToString("\n") { facility ->
            facility?.name ?: ""
        }
        binding.tvFasilitas.text = formattedText
    }
}