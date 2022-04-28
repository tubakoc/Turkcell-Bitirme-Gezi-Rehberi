package com.example.gezirehberim.adapter

import android.graphics.drawable.ShapeDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.gezirehberim.R
import com.example.gezirehberim.constant.Constant.Companion.priorities
import com.example.gezirehberim.view.activity.MainActivity.Companion._context
import org.w3c.dom.Text

class SpinnerAdapter : BaseAdapter {
    override fun getCount() = list.size

    val list= priorities as ArrayList
    constructor(){
        list.add(0,ShapeDrawable())
    }

    override fun getItem(p0: Int): Any {
        return list.size
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(_context!!).inflate(R.layout.spinner_design, null, false)
        if (p0 == 0) {
            view.findViewById<TextView>(R.id.priorityName).text =
                _context?.getString(R.string.priorityChooseText)
        } else {
            view.findViewById<View>(R.id.priorityView).background = list[p0]
            view.findViewById<TextView>(R.id.priorityName).text = "Öncelik $p0"
        }
        return view
    }


}