package com.example.gezirehberim.view.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gezirehberim.MainActivity.Companion._context
import com.example.gezirehberim.R
import com.example.gezirehberim.adapter.SliderViewPagerAdapter
import com.example.gezirehberim.adapter.ViewPagerAdapter
import com.example.gezirehberim.constant.Constant
import com.example.gezirehberim.constant.Constant.Companion.priorities
import com.example.gezirehberim.constant.Priority
import com.example.gezirehberim.databinding.FragmentDetailPlaceBinding
import com.example.gezirehberim.logic.PlaceLogic
import com.example.gezirehberim.model.Picture
import com.example.gezirehberim.model.Place
import com.google.android.material.tabs.TabLayout

class PlaceDetailFragment : Fragment() {

    private var _binding: FragmentDetailPlaceBinding? = null
    private val binding get() = _binding!!
    lateinit var place:Place

    private var selectedPhoto = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailPlaceBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ilk sayfada tıklanınca id yi argument ile buraya paslarız
        val id=arguments?.getInt("id")
        if(id!=null){
            //id yolladığımız için tek değer dönecek bu yüzden sıfırıncı indexi aldık direkt
            place=PlaceLogic.getPlaceDetail(_context!!,id)
        }
      /* binding.descriptionLayout.textView.text=place
           .description
        binding.shortDescriptionLayout.textView.text=place.locationDefinition
        binding.topBar.title.text=place.name*/


        //ekranda deneme amaçlı öncelik değeri gösterir
        binding.priorityDrawable.background = priorities[1]


        //initializeSlider(place.pictureList)

    }


    private fun initializeSlider(picturelist:ArrayList<Picture>) {


        // veritabanından gelen resimler bitmap ya da drawable olarak çevrildikten sonra liste ile slidera gelecek(test görselleri)
//        val list = listOf<Int>(
//            R.drawable.resim,
//            R.drawable.resim,
//            R.drawable.resim,
//            R.drawable.resim,
//            R.drawable.resim,
//            R.drawable.resim,
//            R.drawable.resim,
//            R.drawable.resim,
//            R.drawable.resim
//        )
//        val newPictureList=ArrayList<Bitmap>()
//        for (picture in picturelist){
//            newPictureList.add(picture.convertImagetoBitmap())
//        }

        val adapter = SliderViewPagerAdapter(picturelist)
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