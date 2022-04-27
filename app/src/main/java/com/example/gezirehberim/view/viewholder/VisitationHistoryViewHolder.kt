package com.example.gezirehberim.view.viewholder

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gezirehberim.R
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.model.Visitation

class VisitationHistoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var visitationDescription: TextView
    var visitationDate: TextView
    init {
        visitationDescription = itemView.findViewById(R.id.descriptionTextview)
        visitationDate = itemView.findViewById(R.id.header)



    }
    fun bindData(context : Context, visitation: Visitation) {
        visitationDescription.text = visitation.description

        visitationDate.text = visitation.date

    }
}