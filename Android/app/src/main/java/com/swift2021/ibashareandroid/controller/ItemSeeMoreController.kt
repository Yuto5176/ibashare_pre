package com.swift2021.ibashareandroid.controller

import android.util.Log
import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.swift2021.ibashareandroid.itemSeeMore

class ItemSeeMoreController(private val selectListener: SelectListener) :
    TypedEpoxyController<List<String>>() {

    override fun buildModels(data: List<String>) {
        data.forEach{ item ->
            itemSeeMore {
                itemText(item)
                itemListener(View.OnClickListener {
                    Log.d("hoge:", "item_clicked")
                })
            }

        }
    }


    interface SelectListener{
        fun onSelected()
    }
}
