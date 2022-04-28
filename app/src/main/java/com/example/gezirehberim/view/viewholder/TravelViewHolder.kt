package com.example.gezirehberim.view.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.gezirehberim.R
import com.example.gezirehberim.constant.Constant
import com.example.gezirehberim.constant.convertImagetoBitmap
import com.example.gezirehberim.model.Place

class TravelViewHolder(itemView: View, itemClick: ((position: Int) -> Unit)) :
    RecyclerView.ViewHolder(itemView) {
    var travelImage: ImageView
    var locationName: TextView
    var locationDef: TextView
    var locationState: TextView
    var priorityCircle: View
    var travelDate: TextView
    var csClick: ConstraintLayout

    init {
        travelImage = itemView.findViewById(R.id.travelImage)
        locationName = itemView.findViewById(R.id.locationName)
        locationDef = itemView.findViewById(R.id.locationDef)
        locationState = itemView.findViewById(R.id.locationState)
        priorityCircle = itemView.findViewById(R.id.priorityCircle)
        travelDate = itemView.findViewById(R.id.travelDate)
        csClick = itemView.findViewById(R.id.csClick)

        csClick.setOnClickListener {
            itemClick(adapterPosition)
        }
    }

    fun bindData(place: Place) {
        //  Model oluşturulduktan sonra atama işlemleri yapılacak priority traveldate image eklenecek
        if (place.isVisited == 1 && place.lasVisitDate != null) {
            travelDate.visibility=View.VISIBLE
            travelDate.text=place.lasVisitDate
            priorityCircle.visibility = View.GONE
        } else {
            travelDate.visibility = View.GONE
        }
        locationName.text = place.name
        locationDef.text = place.locationDefinition
        locationState.text = place.description
        priorityCircle.background = Constant.priorities[place.priority]
        if (place.pictureList.size > 0)
            travelImage.setImageBitmap(convertImagetoBitmap(place.pictureList[0].data))
        else
            travelImage.setImageResource(R.drawable.ic_recyclerview_placeholder)
    }

}
