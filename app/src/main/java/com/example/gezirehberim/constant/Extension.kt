package com.example.gezirehberim.constant

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.util.Base64
import java.io.ByteArrayOutputStream

fun convertImagetoBitmap(data:String): Bitmap {
    val imageBytes = Base64.decode(data, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun convertBitmaptoBase64(bitmap: Bitmap):String {
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}
fun checkInternetConnection(cm : ConnectivityManager): Boolean{

        val netInfos = cm.allNetworkInfo

        for (ni in netInfos)
        {
            if ((ni.type == ConnectivityManager.TYPE_WIFI||ni.type== ConnectivityManager.TYPE_MOBILE) && ni.isConnected)
            {
                return true
            }
        }

        return false


}