package com.example.gezirehberim.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gezirehberim.R
import com.example.gezirehberim.adapter.ViewPagerAdapter
import com.example.gezirehberim.constant.Constant
import com.example.gezirehberim.constant.Constant.Companion.priorities
import com.example.gezirehberim.constant.Priority
import com.example.gezirehberim.databinding.FragmentDetailPlaceBinding
import com.google.android.material.tabs.TabLayout

class PlaceDetailFragment : Fragment() {

    private var _binding: FragmentDetailPlaceBinding? = null
    private val binding get() = _binding!!

    var selectedPhoto = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailPlaceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //ekranda deneme amaçlı öncelik değeri gösterir
        binding.priorityDrawable.background = priorities[1]


        initializeSlider()

    }


    private fun initializeSlider() {


        // veritabanından gelen resimler bitmap ya da drawable olarak çevrildikten sonra liste ile slidera gelecek(test görselleri)
        val list = listOf<Int>(
            R.drawable.resim,
            R.drawable.resim,
            R.drawable.resim,
            R.drawable.resim,
            R.drawable.resim,
            R.drawable.resim,
            R.drawable.resim,
            R.drawable.resim,
            R.drawable.resim
        )

        val adapter = ViewPagerAdapter(list)
        binding.slider.viewPager.adapter = adapter
        binding.slider.dots.setupWithViewPager(binding.slider.viewPager)

        binding.slider.dots.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedPhoto = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}