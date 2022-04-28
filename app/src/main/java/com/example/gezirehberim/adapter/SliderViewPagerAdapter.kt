package com.example.gezirehberim.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.gezirehberim.view.activity.MainActivity.Companion._context
import com.example.gezirehberim.constant.convertImagetoBitmap
import com.example.gezirehberim.model.Picture


class SliderViewPagerAdapter(private val sliderList: List<Picture>) : PagerAdapter() {

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
        val date =
            view.findViewById<TextView>(com.example.gezirehberim.R.id.date)
         date.text = sliderList[position].date
        image.setImageBitmap(convertImagetoBitmap(sliderList[position].data))
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}