package com.swift2021.ibashareandroid.model

data class PlaceListData(val placeItemData: List<PlaceItemData>){
    companion object{
        private val placeListData = listOf(
            PlaceItemData(
                "hoge01",
                "place01.png"
            ),
            PlaceItemData(
                "hoge02",
                "place02.png"
            ),PlaceItemData(
                "hoge03",
                "place03.png"
            ),
        )
        fun getPlaceItemListData() = PlaceListData(placeListData)
    }
}
