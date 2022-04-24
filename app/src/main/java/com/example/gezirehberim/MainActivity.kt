package com.example.gezirehberim

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gezirehberim.adapter.ViewPagerAdapter
import com.example.gezirehberim.constant.Constant
import com.example.gezirehberim.databinding.ActivityMainBinding
import com.example.gezirehberim.databinding.FragmentDetailPlaceBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    companion object {
        //burada global context oluşturma amacımız fonksiyonlarda istenen contexleri her defasında göndermekten tasarruf etmek
        @SuppressLint("StaticFieldLeak")
        var _context: Context? = null
    }

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _context = this


    }

}