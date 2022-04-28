package com.example.gezirehberim.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gezirehberim.R
import com.example.gezirehberim.constant.Constant
import com.example.gezirehberim.databinding.FragmentTravelBinding
import com.example.gezirehberim.logic.PlaceLogic
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.view.activity.MainActivity
import com.example.gezirehberim.view.adapter.TravelAdapter

class PlaceVisitedFragment : Fragment() {
    private var _binding: FragmentTravelBinding? = null
    private lateinit var visitedList: ArrayList<Place>
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTravelBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        MainActivity.topBar?.title?.text = getString(R.string.visitedText)
        getVisited()

        //logic veri çek listeye ekle adaptar a bas
        // listeye  modelden data ekleme adaptar e atama kaldı.
    }

    private fun getVisited() {

        visitedList = PlaceLogic.getPlaceList(Constant.VISITED_LIST_ID)
        val adapter = TravelAdapter(visitedList, ::itemClick)
        binding.rvTravel.adapter = adapter

    }

    private fun itemClick(index: Int) {

        //tıklanınca detay sayfasına gidecek
        findNavController().navigate(
            PlaceVisitedFragmentDirections.actionPlaceVisitedFragmentToPlaceDetailFragment(visitedList[index].id))

    }


}