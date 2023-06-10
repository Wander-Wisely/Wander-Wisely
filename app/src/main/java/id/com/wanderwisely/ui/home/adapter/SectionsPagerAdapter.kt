package id.com.wanderwisely.ui.home.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.com.wanderwisely.ui.detail.frament.DescriptionFragment
import id.com.wanderwisely.ui.detail.frament.DetailFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
         when (position) {
            0 ->  fragment = DetailFragment()
            1 ->  fragment = DescriptionFragment()
            else ->  DetailFragment()
        }
        return fragment as Fragment
    }
}