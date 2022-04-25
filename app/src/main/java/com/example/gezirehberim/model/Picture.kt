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
    lateinit var date:String

}