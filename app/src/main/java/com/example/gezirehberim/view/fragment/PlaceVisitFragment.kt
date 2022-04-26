package com.example.gezirehberim.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gezirehberim.databinding.FragmentTravelBinding
import com.example.gezirehberim.model.Place

class PlaceVisitFragment : Fragment() {

    lateinit var binding : FragmentTravelBinding
    lateinit var list : ArrayList<Place>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTravelBinding.inflate(inflater)
        initializeViews()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        //logic veri çek listeye ekle adaptar a bas
    }

    private fun initializeViews()
    {
        val lm = LinearLayoutManager(requireActivity())
        lm.orientation = LinearLayoutManager.VERTICAL
        binding.rvTravel.layoutManager = lm

    }


}