package com.swift2021.ibashareandroid.model

import android.content.Context

class SeeMorePageRepository(private val context: Context) {
    private val name: List<String> = listOf("hoge", "hoge1", "hoge2")
    private val placeData: PlaceListData = PlaceListData.getPlaceItemListData()

    fun getSeeMoreName(): List<String>{
        return name
    }

    fun getPlaceListData(): PlaceListData{
        return placeData
    }
}