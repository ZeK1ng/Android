package ge.dgoginashvili.weatherapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ge.dgoginashvili.weatherapp.fragment.daily_page_fragment
import ge.dgoginashvili.weatherapp.fragment.five_days_fragment


class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){

    var fragmentsList = arrayListOf(daily_page_fragment(),five_days_fragment())

    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }

}