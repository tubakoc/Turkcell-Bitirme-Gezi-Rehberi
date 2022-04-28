package com.example.gezirehberim.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = ArrayList<Fragment>()


    fun fragmentAdd(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    fun fragmentDelete() {
        fragmentList.removeAt(fragmentList.size-1)
    }

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }


}