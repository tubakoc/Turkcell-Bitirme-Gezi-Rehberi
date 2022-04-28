package com.example.gezirehberim.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.gezirehberim.R
import com.example.gezirehberim.view.viewholder.AddPhotoViewHolder

class PhotoAddAdapter(
    val list: List<Uri>,
    val onClickAddPhoto: () -> Unit,
    val onClickRemovePhoto: (index: Int) -> Unit
) :
    RecyclerView.Adapter<AddPhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPhotoViewHolder {
        return AddPhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.photo_add_design, parent, false),
            onClickAddPhoto,
            onClickRemovePhoto
        )
    }

    override fun onBindViewHolder(holder: AddPhotoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}

