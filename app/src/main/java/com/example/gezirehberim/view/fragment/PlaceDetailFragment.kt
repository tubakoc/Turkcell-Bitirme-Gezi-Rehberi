package com.example.gezirehberim.view.fragment

import android.R
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gezirehberim.view.activity.MainActivity.Companion._context
import com.example.gezirehberim.adapter.SliderViewPagerAdapter


import com.example.gezirehberim.constant.Constant.Companion.priorities
import com.example.gezirehberim.constant.checkInternetConnection
import com.example.gezirehberim.constant.convertImagetoBitmap
import com.example.gezirehberim.databinding.FragmentDetailPlaceBinding
import com.example.gezirehberim.logic.PictureLogic
import com.example.gezirehberim.logic.PlaceLogic
import com.example.gezirehberim.model.Picture
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.view.activity.MainActivity
import com.example.gezirehberim.view.activity.MainActivity.Companion.topBar
import com.example.gezirehberim.view.activity.MapsActivity
import com.example.gezirehberim.view.activity.PlaceAdd
import com.example.gezirehberim.view.adapter.VisitationHistoryAdapter
import com.google.android.material.tabs.TabLayout

class PlaceDetailFragment : Fragment() {

    private var _binding: FragmentDetailPlaceBinding? = null
    private val binding get() = _binding!!
    lateinit var place: Place

    private val args: PlaceDetailFragmentArgs by navArgs()
    private lateinit var pictureList: ArrayList<Picture>


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

        topBarInitialize()

    }

    override fun onResume() {
        super.onResume()
        setView()
    }

    private fun topBarInitialize() {
        MainActivity.topBar!!.backButton.setOnClickListener { findNavController().navigateUp() }
        MainActivity.topBar!!.backButton.visibility=View.VISIBLE
    }

    private fun setView() {
        //ilk sayfada tıklanınca id yi argument ile buraya paslarız
        if (args.id != null) {
            //id yolladığımız için tek değer dönecek bu yüzden sıfırıncı indexi aldık direkt
            place = PlaceLogic.getPlaceDetail(args.id)
        }
        initializeVisitation()
        binding.shortDescriptionLayout.showLocationButton.visibility = View.VISIBLE
        binding.shortDescriptionLayout.showLocationButton.setOnClickListener(btnClickForLocation)
        topBar!!.title.text = place
            .name
        binding.shortDescriptionLayout.header.text = "Yer Kısa Tanım"
        binding.shortDescriptionLayout.placeShortDescriptionTextview.hint = place.locationDefinition
        binding.shortDescriptionLayout.placeShortDescriptionTextview.isEnabled=false
        binding.descriptionLayout.header.text = "Kısa Açıklama"
        binding.descriptionLayout.descriptionTextview.hint = place
            .description
        binding.addVisitButton.setOnClickListener(btnClickAddVisit)
        //ekranda deneme amaçlı öncelik değeri gösterir
        binding.priorityDrawable.background = priorities[place.priority]
        pictureList = PictureLogic.returnPicturesForSlider(place)

        initializeSlider()
    }

    private val btnClickForLocation = View.OnClickListener {
        goToMapsActivity()
    }
    private val btnClickAddVisit=View.OnClickListener {
        val intent=Intent(requireContext(),PlaceAdd::class.java)
        intent.putExtra("placeId",place.id)
        intent.putExtra("placeName", place.name)
        startActivity(intent)
    }

    private fun goToMapsActivity() {
        val cm =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (!checkInternetConnection(cm)) {

            Toast.makeText(
                context,
                "Lütfen internet bağlantınızı kontrol ediniz",
                Toast.LENGTH_LONG
            ).show()


        } else {
            val intent = Intent(requireContext(), MapsActivity::class.java)
            intent.putExtra("lat", place.latitude)
            intent.putExtra("long", place.longitude)
            intent.putExtra(
                "locationName", place
                    .name
            )
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }


    private fun initializeSlider() {


        val adapter = SliderViewPagerAdapter(pictureList,::showFullScreenImage)
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
        MainActivity.topBar!!.backButton.visibility=View.GONE
    }

    private fun initializeVisitation() {
        if (place.visitationList.size > 0) {
            val adapter = VisitationHistoryAdapter(place.visitationList)
            binding.recylerVisitHistory.adapter = adapter


        } else {
            binding.visitHistoryHeader.visibility = View.GONE
            binding.recylerVisitHistory.visibility = View.GONE
        }
    }

    private fun showFullScreenImage(position: Int) {
        val view = LayoutInflater.from(requireContext())
            .inflate(com.example.gezirehberim.R.layout.full_screen_image, null, false)

        view.findViewById<ImageView>(com.example.gezirehberim.R.id.image).setImageBitmap(
            convertImagetoBitmap(pictureList[position].data)
        )

        val dg= Dialog(requireContext(), R.style.Theme_Material_Light_NoActionBar_Fullscreen)
        dg.setContentView(view)
        dg.show()




    }

}