package com.swift2021.ibashareandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.swift2021.ibashareandroid.R
import com.swift2021.ibashareandroid.controller.ItemSeeMoreController
import com.swift2021.ibashareandroid.databinding.FragmentSeeMorePageBinding
import com.swift2021.ibashareandroid.viewmodel.SeeMorePageViewModel
import kotlinx.android.synthetic.main.fragment_place_detail_page.button_send

class SeeMorePageFragment : Fragment() {
    private lateinit var binding: FragmentSeeMorePageBinding
    private val viewModel: SeeMorePageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_see_more_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setBackButtonEvent()
    }

    private fun setBackButtonEvent() {
        button_send.setOnClickListener {
            findNavController().navigate(R.id.action_see_more_to_top)
        }
    }

    private fun initEpoxy(){
        val controller = ItemSeeMoreController(
            object : ItemSeeMoreController.SelectListener{
                override fun onSelected() {
                    Toast.makeText(context, "click", Toast.LENGTH_SHORT)
                }
            }
        )
        binding.recyclerView.setController(controller)
        viewModel.nameList.observe(viewLifecycleOwner){
            controller.setData(it)
        }
    }
}