package com.example.foodcourt.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.foodcourt.R
import com.example.foodcourt.adapter.PopularAdapter
import com.example.foodcourt.databinding.FragmentHome2Binding
import com.google.android.material.slider.Slider


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHome2Binding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHome2Binding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagelist = ArrayList<SlideModel>()
        imagelist.add(SlideModel(R.drawable.banner1, ScaleTypes.FIT))
        imagelist.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imagelist.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imagelist)
        imageSlider.setImageList(imagelist, ScaleTypes.FIT)

        val foodname = listOf("Kottu", "Fried Rice", "Burger")
        val price = listOf("$5", "$20" , "$15", "$7")
        val image = listOf(R.drawable.menu1,R.drawable.menu2,R.drawable.menu3)

        val adapter = PopularAdapter(foodname,price,image)
        binding.PopularrecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.PopularrecyclerView.adapter = adapter
    }


    companion object {

    }
}