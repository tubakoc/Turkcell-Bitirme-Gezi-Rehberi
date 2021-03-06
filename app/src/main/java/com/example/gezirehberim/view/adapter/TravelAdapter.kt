package com.example.gezirehberim.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gezirehberim.R
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.view.viewholder.TravelViewHolder

//data model olusturulduktan sonra düzenlenecek Array list tip düzeltilecek
class TravelAdapter(
    var liste: ArrayList<Place>,
    val itemClick: (position: Int) -> Unit,
    val isVisitedOrVisit: Int
) : RecyclerView.Adapter<TravelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rvc_travel, parent, false)
        return TravelViewHolder(v, itemClick, isVisitedOrVisit)
    }

    override fun onBindViewHolder(holder: TravelViewHolder, position: Int) {
        holder.bindData(liste.get(position))
    }

    override fun getItemCount(): Int {
        return liste.size

    }
}

