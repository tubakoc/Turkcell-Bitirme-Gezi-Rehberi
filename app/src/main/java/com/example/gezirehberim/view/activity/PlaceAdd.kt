package com.example.gezirehberim.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gezirehberim.databinding.ActivityPlaceAddBinding

class PlaceAdd : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}