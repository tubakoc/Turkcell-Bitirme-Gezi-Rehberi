package com.example.gezirehberim.view.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gezirehberim.R
import com.example.gezirehberim.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var lat: Double? = null
    private var long: Double? = null
    private var locationName: String? = null
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null
    private var lastLatLang: LatLng?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //konum göster ve konum ekle için aynı ekran kullanılacağı için böyle bir yapı seçtim
        //Matematiksel olarak koordinatlar 360dan fazla olamaz
        lat = intent.getDoubleExtra("lat", 361.0)
        long = intent.getDoubleExtra("long", 361.0)


    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true

        //location name detay ekranından geldiği için sadece onu kontrol etsek yeterli
        if (lat==361.0||long==361.0) {
            val location = LatLng(lat!!, long!!)
          //  mMap.isMyLocationEnabled = true
            mMap.addMarker(
                MarkerOptions().position(location).title(intent.getStringExtra("locationName"))
            )

            mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
            binding.saveButtonLayout.btnMapApply.text="Kaydet"
            binding.saveButtonLayout.btnMapApply.setOnClickListener(btnClickForAddPlace)

        } else {

            binding.saveButtonLayout.btnMapApply.setOnClickListener(btnClickForNavigation)
            binding.saveButtonLayout.btnMapApply.text="Git"
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//            locationListener = LocationListener { p0 ->
//                val currentLocation = LatLng(p0.latitude, p0.longitude)
//                mMap.clear()
//                mMap.addMarker(MarkerOptions().position(currentLocation).title("Konumunuz"))
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16f))
//                //lokasyon veya konum değişince nele ryapılacak
//                val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
//
//                try {
//                    val locationlist = geocoder.getFromLocation(p0.latitude, p0.longitude, 1)
//                    if (locationlist.size > 0) {
//                        println(locationlist.get(0).toString())
//
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//
//                }
//            }
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            } else {
//                locationManager!!.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER, 100, 10f,
//                    locationListener!!
//
//                )

                val lastlocation =
                    locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (lastlocation != null) {
                    lastLatLang = LatLng(lastlocation.latitude, lastlocation.longitude)
                    mMap.addMarker(MarkerOptions().position(lastLatLang).title("Konumunuz"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatLang, 16f))
                }
            }


        }
        mMap.setOnMapLongClickListener(longClickListener)
    }

    private val btnClickForNavigation = View.OnClickListener {
        openGoogleMapsNavigation(this, lat!!, long!!)
    }
    private val btnClickForAddPlace = View.OnClickListener {
        backToAddPlacePage(lastLatLang)
    }

    private fun openGoogleMapsNavigation(
        context: Context,
        latitude: Double,
        longitude: Double
    ) {
        val googleMapsUrl = "google.navigation:q=$latitude,$longitude"
        val uri = Uri.parse(googleMapsUrl)

        val googleMapsPackage = "com.google.android.apps.maps"
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage(googleMapsPackage)
        }

        context.startActivity(intent)
    }

    private val longClickListener = GoogleMap.OnMapLongClickListener { p0 ->
        mMap.clear()



            try {

                    lastLatLang = LatLng(p0.latitude, p0.longitude)
                    mMap.addMarker(MarkerOptions().position(lastLatLang!!).title("Seçilen Konum"))

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatLang!!, 16f))

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    private fun backToAddPlacePage(lastLatLang: LatLng?) {
        val intent = Intent()

        intent.putExtra("lat", lastLatLang?.latitude)
        intent.putExtra("long",lastLatLang?.longitude)

        setResult(RESULT_OK, intent)
        finish()
    }
    }





