package com.example.gezirehberim.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gezirehberim.R
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.model.Visitation
import com.example.gezirehberim.view.viewholder.TravelViewHolder
import com.example.gezirehberim.view.viewholder.VisitationHistoryViewHolder

class VisitationHistoryAdapter(val context: Context, var list:ArrayList<Visitation>):RecyclerView.Adapter<VisitationHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitationHistoryViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.description_layout,parent,false)
        return VisitationHistoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: VisitationHistoryViewHolder, position: Int) {
        holder.bindData(context, list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}