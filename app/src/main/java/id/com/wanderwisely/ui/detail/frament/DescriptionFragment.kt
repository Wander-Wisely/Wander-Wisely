package id.com.wanderwisely.ui.detail.frament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.gms.maps.SupportMapFragment
import id.com.wanderwisely.R
import id.com.wanderwisely.ui.detail.DetailActivity


class DescriptionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        val description = arguments?.getString("DESCRIPTION_EXTRA")
        val textView = view?.findViewById<TextView>(R.id.description)
        textView?.text = description
    }

    companion object{
        const val DESCRIPTION_EXTRA = "description_extra"
    }
}