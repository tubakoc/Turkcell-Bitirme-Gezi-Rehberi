package com.example.gezirehberim.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.gezirehberim.R
import com.example.gezirehberim.adapter.PhotoAddAdapter
import com.example.gezirehberim.constant.*
import com.example.gezirehberim.databinding.ActivityPlaceAddBinding
import com.example.gezirehberim.logic.PlaceLogic
import com.example.gezirehberim.model.Picture
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.view.adapter.PlacePhotoAdapter
import java.io.File

class PlaceAdd : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceAddBinding
    private var lat: Double = 361.0
    private var long: Double = 361.0


    private var priority = 0
    private lateinit var choosePhotoList: ArrayList<Uri>
    private var choosePhotoListBitmap = ArrayList<Bitmap>()
    private lateinit var adapter: PhotoAddAdapter
    private var isPLaceOrVisit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPlaceOrVisit()


        binding.topBar.backButton.visibility = View.VISIBLE
        binding.topBar.title.text = "Yer Ekle"

        setOnClicks()
        spinnerInitialize()
        initializePhotoAddRecycler()

    }

    private fun checkPlaceOrVisit() {

        isPLaceOrVisit = intent.getBooleanExtra("placeOrVisit", false)

        if (isPLaceOrVisit) {
            setPlaceInitialize()
        } else {
            setVisitInitialize()
        }
    }

    private fun setVisitInitialize() {

    }

    private fun setPlaceInitialize() = binding.apply {

        visitDate.header.text = "Yer adı"
        visitDate.placeShortDescriptionTextview.hint = "Yer adı giriniz"

        placeShortDescription.placeShortDescriptionTextview.hint = "Tanım bilgisi giriniz"
        visitDescription.descriptionTextview.hint = "Kısa açıklama giriniz"

    }

    private fun spinnerInitialize() {
        val adapter = com.example.gezirehberim.adapter.SpinnerAdapter()
        binding.spinnerLayout.prioritySpinner.adapter = adapter

        binding.spinnerLayout.prioritySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    priority = p2
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
    }

    private fun setOnClicks() = binding.apply {
        saveButtonLayout.btnMapApply.setOnClickListener { savePlace() }

        addLocationButton.setOnClickListener(btnClickAddLocation)
        topBar.backButton.setOnClickListener { finish() }
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
            lat = result.data?.getDoubleExtra("lat",361.0)!!
            long = result.data?.getDoubleExtra("long",361.0)!!
            if (lat==361.0||long==361.0) {
               Toast.makeText(this,"Konum ekleme işlemi başarısız oldu lütfen tekrar deneyiniz.",Toast.LENGTH_LONG).show()
            }
            else{
                Log.d("konum", "$lat  $long")
            }

        }
    }

    private fun savePlace() {

        val placeName = binding.visitDate.placeShortDescriptionTextview.text.toString()
        val placeShortDescription =
            binding.placeShortDescription.placeShortDescriptionTextview.text.toString()
        val description = binding.visitDescription.descriptionTextview.text.toString()



        if (choosePhotoList.size > 1 && lat != 361.0 && long != 361.0 && placeName.isNotEmpty() && placeShortDescription.isNotEmpty() && description.isNotEmpty() && priority != 0) {
            val place = Place()
            place.latitude = lat
            place.longitude = long
            place.name = placeName
            place.isVisited = Constant.TO_BE_VISITED_LIST_ID
            place.description = description
            place.priority = priority - 1
            place.locationDefinition = placeShortDescription
            place.pictureList = setPictureList()
            PlaceLogic.addPlace(place)
            finish()
        } else {
            showToast(MainActivity._context!!.getString(R.string.info_empty))
        }


    }

    private fun setPictureList(): ArrayList<Picture> {

        val list = ArrayList<Picture>()
        val pc = Picture()

        choosePhotoListBitmap.forEach {
            pc.data = convertBitmaptoBase64(it)
            pc.date = getCurrentDate()
            list.add(pc)
        }

        return list
    }


    private fun initializePhotoAddRecycler() {
        choosePhotoList = arrayListOf<Uri>(Uri.parse(""))
        adapter = PhotoAddAdapter(choosePhotoList, ::AddPhoto, ::RemoveChoosePhoto)
        binding.photoAddRecycler.adapter = adapter

    }


    private fun AddPhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraOrGalleryCheckPermission()
        }
    }

    private fun RemoveChoosePhoto(index: Int) {
        if (choosePhotoList.size == 10)
            choosePhotoList.add(Uri.parse(""))

        choosePhotoList.removeAt(index)
        adapter.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun cameraOrGalleryCheckPermission() {

        val requestList = ArrayList<String>()

        var permissionState = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!permissionState) {
            requestList.add(android.Manifest.permission.CAMERA)
        }

        permissionState = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        if (!permissionState) {
            requestList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }


        permissionState = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        if (!permissionState) {
            requestList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }


        if (requestList.size == 0) {
            showPopup()
        } else
            requestPermissions(requestList.toTypedArray(), Constant.REQUEST_CODE_CAMERA_AND_GALLERY)


    }

    private lateinit var chooseGetCameraUri: Uri
    private fun openCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        chooseGetCameraUri = FileProvider.getUriForFile(this, packageName, createFileImage(this))

        intent.putExtra(MediaStore.EXTRA_OUTPUT, chooseGetCameraUri)

        cameraRl.launch(intent)

    }

    private fun showPopup() {
        val alert = android.app.AlertDialog.Builder(this).setTitle("Hangi kaynaktan fotoğraf yükleyeceksiniz?")
            .setPositiveButton("Galeri") { d, _ ->
                openGallery(); d.dismiss()
            }.setNegativeButton("Kamera") { d, _ ->
                openCamera(); d.dismiss()
            }.show()

    }


    private fun openGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        galleryRl.launch(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val galleryRl =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {


            if (it.resultCode == Activity.RESULT_OK && it.data?.data != null) {
                photoAddList(it.data?.data!!)
                val imageStream = this.contentResolver.openInputStream(it.data?.data!!)
                val bitmap = BitmapFactory.decodeStream(imageStream);
                choosePhotoListBitmap.add(bitmap)
            } else
                showToast("İşlem reddedildi")
        }


    var cameraRl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        if (it.resultCode == RESULT_OK) {
            photoAddList(chooseGetCameraUri)
            val imageStream = this.contentResolver.openInputStream(chooseGetCameraUri)
            val bitmap = BitmapFactory.decodeStream(imageStream);
            choosePhotoListBitmap.add(bitmap)
        } else
            showToast("İşlem reddedildi")

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun photoAddList(uri: Uri) {
        choosePhotoList.removeAt(choosePhotoList.size - 1)
        choosePhotoList.add(uri)
        if (choosePhotoList.size < 10)
            choosePhotoList.add(Uri.parse(""))
        adapter.notifyDataSetChanged()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        var allowDenied = true

        for (gr in grantResults) {
            if (gr != PackageManager.PERMISSION_GRANTED) {
                allowDenied = false
                break
            }
        }

        if (!allowDenied) {

            var notShow = false

            for (permission in permissions) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        permission
                    )
                ) {

                } else if (ContextCompat.checkSelfPermission(
                        this,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED
                ) {

                } else {

                    notShow = true
                    break
                }

            }

            if (notShow) {
                androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("İzin gerekli")
                    .setMessage("Ayarlara giderek tüm izinleri onaylayınız")
                    .setPositiveButton("Ayarlar") { dialog, which ->
                        openSetting()
                    }.setNegativeButton("Vazgeç", null).show()
            }

        } else {

            when (requestCode) {

                Constant.REQUEST_CODE_CAMERA_AND_GALLERY -> showToast("Galeri ve kamera izni verildi")

            }

        }

    }

    private fun openSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)

    }





}


