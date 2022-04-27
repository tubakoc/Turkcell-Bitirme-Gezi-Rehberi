package com.example.gezirehberim.view.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gezirehberim.R
import com.example.gezirehberim.view.viewholder.PlacePhotoViewHolder

//modele göre liste tipi seçilecek

class PlacePhotoAdapter(val context: Context, var liste: ArrayList<Uri>, val itemClick: ((position : Int)->Unit)?, var deletePhotoClick: (position : Int) -> Unit, var addPhotoClick: (position : Int) -> Unit) : RecyclerView.Adapter<PlacePhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacePhotoViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rvc_travel,parent,false)
        return PlacePhotoViewHolder(v,itemClick!!,deletePhotoClick,addPhotoClick)
    }

    override fun onBindViewHolder(holder: PlacePhotoViewHolder, position: Int) {
        holder.bindData(context, liste.get(position))
    }

    override fun getItemCount(): Int {
        return liste.size

    }
}