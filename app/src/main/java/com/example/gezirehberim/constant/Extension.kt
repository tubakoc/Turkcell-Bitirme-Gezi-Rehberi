package com.example.gezirehberim.constant

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.os.Environment
import android.util.Base64
import android.widget.Toast
import com.example.gezirehberim.view.activity.MainActivity
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun convertImagetoBitmap(data:String): Bitmap {
    val imageBytes = Base64.decode(data, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}


fun showToast(message: String) {
    Toast.makeText(
        MainActivity._context!!,
        message,
        Toast.LENGTH_LONG
    ).show()
}

fun createFileImage(context: Context): File {
    val dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("resim", ".jpg", dir)
}

fun getCurrentDate(): String {
    return   SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
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