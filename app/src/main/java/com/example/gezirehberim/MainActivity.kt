package com.example.gezirehberim

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gezirehberim.databinding.ActivityMainBinding
import com.example.gezirehberim.databinding.TabLayoutBinding
import com.example.gezirehberim.view.adapter.ViewPagerAdapter
import com.example.gezirehberim.view.fragment.PlaceVisitFragment
import com.example.gezirehberim.view.fragment.PlaceVisitedFragment
import com.google.android.material.tabs.TabLayoutMediator

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

        createViewPager()
        changeViewPagerOrTabLayout()
        createTab()
        //tıklama olayları
        setOnClickListeners()


    }


    private fun changeViewPagerOrTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { _, _ -> }.attach()
    }

    private fun createViewPager(){
        val adapter = ViewPagerAdapter(this)

        /* BURAYA TUBA'nın EKRANLARI IMPORT EDİLECEK(2 tane gezilecekler ve gezdiklerim)*/
        adapter.fragmentAdd(PlaceVisitFragment())
        adapter.fragmentAdd(PlaceVisitedFragment())

        binding.viewpager.adapter = adapter

    }

    private fun createTab(){
        var tab = TabLayoutBinding.inflate(layoutInflater)
        tab.tvImage.setImageResource(R.drawable.ic_to_visit)
        tab.tvName.text = resources.getString(R.string.toVisitText)
        binding.tabLayout.getTabAt(0)?.customView = tab.root


        tab = TabLayoutBinding.inflate(layoutInflater)
        tab.tvImage.setImageResource(R.drawable.ic_visited)
        tab.tvName.text = resources.getString(R.string.visitedText)
        binding.tabLayout.getTabAt(1)?.customView = tab.root

    }

    private fun setOnClickListeners()=binding.apply{

        //yer ekleme ekranına gidilecek (activity)
        addPlaceButton.setOnClickListener{}



    }

}