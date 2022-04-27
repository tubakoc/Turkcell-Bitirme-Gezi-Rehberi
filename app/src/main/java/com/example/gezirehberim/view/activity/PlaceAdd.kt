package com.example.gezirehberim.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.gezirehberim.constant.convertBitmaptoBase64
import com.example.gezirehberim.databinding.ActivityPlaceAddBinding
import com.example.gezirehberim.model.Picture
import java.io.File

class PlaceAdd : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceAddBinding
    private var lat: Double = 0.0
    private var long: Double = 0.0
    lateinit var geciciResimUri: Uri
    var uriList = ArrayList<Uri>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addLocationButton.setOnClickListener(btnClickAddLocation)

    }

    private val btnClickAddLocation = View.OnClickListener {
        goToMapsActivity()
    }


    private fun goToMapsActivity() {
        val intent = Intent(this, MapsActivity::class.java)
        resultLauncher.launch(intent)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ::getLocationResult
    )

    private fun getLocationResult(result: ActivityResult) {

        if (result.resultCode == RESULT_OK) {
            //konum alındı
            lat = result.data?.getDoubleExtra("lat", 361.0)!!
            long = result.data?.getDoubleExtra("lat", 361.0)!!
            if (lat == 361.0 || long == 361.0) {
                Toast.makeText(
                    this,
                    "Konum ekleme işlemi başarısız oldu lütfen tekrar deneyiniz.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Log.d("konum", "$lat  $long")
            }

        }
    }


    @SuppressLint("NewApi")
    fun cameraPermissionControl() {
        val requestList = ArrayList<String>()

        var permissionStatus = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!permissionStatus) {
            requestList.add(Manifest.permission.CAMERA)
        }

        permissionStatus = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        if (!permissionStatus) {
            requestList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        permissionStatus = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED


        if (!permissionStatus) {
            requestList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (requestList.size == 0) {
            selectSource()
        } else {
            requestPermissions(requestList.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for (gr in grantResults) {
            if (gr != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "İzinlerin tümü verilmedi", Toast.LENGTH_LONG).show()

                return
            }
        }

        selectSource()
    }


    fun selectSource() {
        val adb = AlertDialog.Builder(this)
        adb.setTitle("Kaynak Seç")
            .setMessage("Fotoğrafı ekleyeceğiniz kaynağı seçiniz.")
            .setPositiveButton("Kamera") { dialog, which ->
                openCamera()
            }
            .setNegativeButton("Galeri") { dialog, which ->
                openGallery()
            }
            .show()
    }

    private fun openGallery() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galeriRl.launch(intent)
    }

    private fun openCamera() {
        resimDosyasiOlustur()
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, geciciResimUri)
        cameraRl.launch(intent)
    }

    private fun resimDosyasiOlustur() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        File.createTempFile("resim", ".jpg", dir).apply {
            geciciResimUri = FileProvider.getUriForFile(this@PlaceAdd, packageName, this)
        }
    }


    var cameraRl = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if (it.resultCode == RESULT_OK) {
            uriList.add(geciciResimUri)
            listeGuncelle()
        }
    }

    private fun listeGuncelle() {
        Toast.makeText(this, "Liste güncellend", Toast.LENGTH_LONG).show()
    }


    var galeriRl =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                uriList.add(result.data!!.data as Uri)
                val piture = Picture()
                piture.id = 0
                piture.data = convertBitmaptoBase64(getCapturedImage(uriList[0]))
                listeGuncelle()
            }
        }

    private fun getCapturedImage(selectedPhotoUri: Uri): Bitmap {
        val bitmap = when {
            Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
                this.contentResolver,
                selectedPhotoUri
            )
            else -> {
                val source = ImageDecoder.createSource(this.contentResolver, selectedPhotoUri)
                ImageDecoder.decodeBitmap(source)
            }
        }
return bitmap
    }
}