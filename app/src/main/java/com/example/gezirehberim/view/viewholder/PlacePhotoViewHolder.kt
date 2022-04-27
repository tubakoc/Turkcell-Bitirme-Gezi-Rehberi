package com.example.gezirehberim.view.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gezirehberim.R
import com.example.gezirehberim.model.Place

class PlacePhotoViewHolder(itemView : View, itemClick : ((position : Int)->Unit),var deletePhotoClick : (position : Int) -> Unit,  var addPhotoClick : (position : Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
    var photo : ImageView
    var removeButton : ImageView
    var addPhoto : TextView

    init {
        photo = itemView.findViewById(R.id.photo)
        removeButton = itemView.findViewById(R.id.removeButton)
        addPhoto = itemView.findViewById(R.id.addPhoto)

        itemView.setOnClickListener {
            itemClick(adapterPosition)
        }
    }

    fun bindData(context : Context, place : Place) {
        //  Model oluşturulduktan sonra atama işlemleri yapılacak priority traveldate image eklenecek

    }
}