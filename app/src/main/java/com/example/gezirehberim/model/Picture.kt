package com.example.gezirehberim.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import kotlin.properties.Delegates

class Picture {
    var id by Delegates.notNull<Int>()
    var placeId: Int? = null
    var visitationId: Int? = null
    lateinit var data: String
    fun convertImagetoBitmap(): Bitmap {
        val imageBytes = Base64.decode(data, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
    fun convertBitmaptoBase64(bitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        this.data=Base64.encodeToString(b, Base64.DEFAULT)

    }
}