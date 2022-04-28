package com.example.gezirehberim.view.viewholder

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.gezirehberim.R


class AddPhotoViewHolder(
    view: View,
    val onClickAddPhoto: () -> Unit,
    val onClickRemoovePhoto: (index: Int) -> Unit,

) : RecyclerView.ViewHolder(view) {

    val imageView: ImageView = view.findViewById(R.id.photo)
    val removeButton: ImageView = view.findViewById(R.id.removeButton)
    val photoLayout: ConstraintLayout = view.findViewById(R.id.photoLayout)
    val photoAdd: ConstraintLayout = view.findViewById(R.id.photoAddLayout)


    fun bind(uri: Uri) {

        if (uri.toString().isNotEmpty()) {
            photoLayout.visibility = View.VISIBLE
            photoAdd.visibility = View.GONE
            imageView.setImageURI(uri)
            removeButton.setOnClickListener { onClickRemoovePhoto(adapterPosition) }
        } else  {
            photoLayout.visibility = View.GONE
            photoAdd.visibility = View.VISIBLE
            photoAdd.setOnClickListener { onClickAddPhoto() }
        }


    }
}
