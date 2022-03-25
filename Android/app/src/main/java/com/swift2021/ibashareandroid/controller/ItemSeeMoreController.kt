package com.swift2021.ibashareandroid.controller

import android.util.Log
import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.swift2021.ibashareandroid.itemSeeMore
import com.swift2021.ibashareandroid.model.PlaceListData

class ItemSeeMoreController(private val selectListener: SelectListener) :
    TypedEpoxyController<PlaceListData>() {

    override fun buildModels(data: PlaceListData) {
        data.placeItemData.forEach{ item ->
            itemSeeMore {
                id(modelCountBuiltSoFar)
                itemText(item.title)
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
