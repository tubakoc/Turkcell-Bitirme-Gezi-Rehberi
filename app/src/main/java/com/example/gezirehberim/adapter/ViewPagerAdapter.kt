package com.example.gezirehberim.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.gezirehberim.MainActivity.Companion._context


class ViewPagerAdapter(private val sliderList: List<Int>) : PagerAdapter() {

    override fun getCount() = sliderList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`;
    }


    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = _context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View =
            inflater.inflate(com.example.gezirehberim.R.layout.item_viewpager_layout, null)
        val image =
            view.findViewById<ImageView>(com.example.gezirehberim.R.id.imageViewPager)

        image.setImageResource(sliderList[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}