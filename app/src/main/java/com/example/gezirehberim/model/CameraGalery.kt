package com.example.gezirehberim.model

import android.content.Context
import androidx.core.content.ContextCompat
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.IOException

object CameraGalery{

    lateinit var geciciResimUri : Uri
    var uriList = ArrayList<Uri>()


    @SuppressLint("NewApi")
    fun cameraPermissionControl(context : Context)
    {
        val requestList = ArrayList<String>()

        var permissionStatus =  ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        if (!permissionStatus)
        {
            requestList.add(Manifest.permission.CAMERA)
        }

        permissionStatus = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

        if (!permissionStatus)
        {
            requestList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        permissionStatus = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED


        if (!permissionStatus)
        {
            requestList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (requestList.size == 0)
        {
            selectSource(context)
        }
        else
        {
          //  requestPermissions(requestList.toTypedArray(),0)
        }
    }

    @Throws(IOException::class)
    fun createImageFile()
    {
        //eksik kod eklenecek
    }





    fun selectSource(context : Context) {
        val adb = AlertDialog.Builder(context)
        adb.setTitle("Kaynak Seç")
            .setMessage("Fotoğrafı ekleyeceğiniz kaynağı seçiniz.")
            .setPositiveButton("Kamera") { dialog, which ->
                openCamera()
            }
            .setNegativeButton("Galeri") { dialog, which ->
                openGalery()
            }
            .show()
    }

    private fun openCamera() {
        createImageFile()
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, geciciResimUri)
        //cameraRl.launch(intent)
    }


    private fun openGalery() {
        TODO("Not yet implemented")
    }

    fun delete(position : Int)
    {

    }


}