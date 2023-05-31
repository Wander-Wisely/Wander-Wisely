package id.com.wanderwisely.ui.detail.frament

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import id.com.wanderwisely.R
import id.com.wanderwisely.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding

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
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}