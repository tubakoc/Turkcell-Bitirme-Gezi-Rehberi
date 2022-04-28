package com.example.gezirehberim.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.gezirehberim.R
import com.example.gezirehberim.constant.Constant
import com.example.gezirehberim.databinding.ActivityMainBinding
import com.example.gezirehberim.databinding.TabLayoutBinding
import com.example.gezirehberim.databinding.TopBarBinding
import com.example.gezirehberim.view.adapter.ViewPagerAdapter
import com.example.gezirehberim.view.fragment.PlaceDetailFragment
import com.example.gezirehberim.view.fragment.PlaceVisitFragment
import com.example.gezirehberim.view.fragment.PlaceVisitedFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    companion object {
        //burada global context oluşturma amacımız fonksiyonlarda istenen contexleri her defasında göndermekten tasarruf etmek
        @SuppressLint("StaticFieldLeak")
        var _context: Context? = null

        var topBar: TopBarBinding? = null
    }

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _context = this

        //her sayfada ortak olduğu main activity içerisinde import edip global değişken olarak kullanıyoruz
        topBar = binding.topBar

        setOnClickListeners()
        navControllerInitialize()

    }

    private fun navControllerInitialize() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

    }

    private fun setOnClickListeners() = binding.apply {

        //yer ekleme ekranına gidilecek (activity)
        addPlaceButton.setOnClickListener {

            val intent = Intent(this@MainActivity, PlaceAdd::class.java)
            intent.putExtra("placeOrVisit", Constant.ADD_PLACE)
            startActivity(intent)

        }
    }





}