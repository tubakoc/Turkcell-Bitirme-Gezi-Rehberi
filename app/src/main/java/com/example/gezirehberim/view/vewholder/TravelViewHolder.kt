package com.example.gezirehberim.view.vewholder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gezirehberim.R
import com.example.gezirehberim.model.Place

class TravelViewHolder (itemView : View, itemClick : ((position : Int)->Unit)) : RecyclerView.ViewHolder(itemView) {
    var travelImage: ImageView
    var locationName: TextView
    var locationDef: TextView
    var locationState: TextView
    var priorityCircle: ImageView
    var travelDate: TextView

    init {
        travelImage = itemView.findViewById(R.id.travelImage)
        locationName = itemView.findViewById(R.id.locationName)
        locationDef = itemView.findViewById(R.id.locationDef)
        locationState = itemView.findViewById(R.id.locationState)
        priorityCircle = itemView.findViewById(R.id.priorityCircle)
        travelDate = itemView.findViewById(R.id.travelDate)


    }


    fun bindData(context : Context,place : Place) {
        //  Model oluşturulduktan sonra atama işlemleri yapılacak priority traveldate image eklenecek

        locationName.text = place.name
        locationDef.text = place.locationDefinition
        locationState.text = place.description


     }



    }
