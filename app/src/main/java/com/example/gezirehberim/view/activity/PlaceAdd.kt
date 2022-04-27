package com.example.gezirehberim.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gezirehberim.databinding.ActivityPlaceAddBinding

class PlaceAdd : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceAddBinding
    private var lat: Double = 0.0
    private var long: Double = 0.0
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
        // intent.putExtra("product", productList.get(position))
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
            long = result.data?.getDoubleExtra("lat",361.0)!!
            if (lat==361.0||long==361.0) {
               Toast.makeText(this,"Konum ekleme işlemi başarısız oldu lütfen tekrar deneyiniz.",Toast.LENGTH_LONG).show()
            }
            else{
                Log.d("konum", "$lat  $long")
            }

        }
    }
}