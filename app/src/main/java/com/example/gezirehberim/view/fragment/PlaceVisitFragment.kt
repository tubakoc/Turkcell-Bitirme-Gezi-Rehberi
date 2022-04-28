package com.example.gezirehberim.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gezirehberim.R
import com.example.gezirehberim.constant.Constant
import com.example.gezirehberim.databinding.FragmentTravelBinding
import com.example.gezirehberim.logic.PlaceLogic
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.view.activity.MainActivity
import com.example.gezirehberim.view.adapter.TravelAdapter

class PlaceVisitFragment : Fragment() {

    private var _binding: FragmentTravelBinding? = null
    private val binding get() = _binding!!
    private lateinit var list: ArrayList<Place>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTravelBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        MainActivity.topBar?.title?.text = getString(R.string.toVisitText)
        //logic veri çek listeye ekle adaptar a bas

        getToBeVisited()
    }


    private fun getToBeVisited() {

        val toBeVisitedList = PlaceLogic.getPlaceList(Constant.TO_BE_VISITED_LIST_ID)
        val adapter = TravelAdapter(toBeVisitedList, ::itemClick)
        binding.rvTravel.adapter = adapter


    }

    private fun itemClick(index: Int) {

        //tıklanınca detay sayfasına gidecek

    }


}