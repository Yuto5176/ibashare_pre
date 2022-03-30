package com.swift2021.ibashareandroid.model

class TopPageRepository {
    private val placeData: List<List<String>> = initPlaceList()

    fun getPlaceNameTop(): List<List<String>> {
        return placeData
    }

    private fun initPlaceList(): List<List<String>> {
        return listOf(
            listOf("hoge", "hoge1", "hoge", "hoge1"),
            listOf("hoge", "hoge1", "hoge", "hoge1"),
            listOf("hoge", "hoge1", "hoge", "hoge1"),
            listOf("hoge", "hoge1", "hoge", "hoge1"),
        )
    }

}