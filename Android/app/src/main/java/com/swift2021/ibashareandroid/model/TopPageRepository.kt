package com.swift2021.ibashareandroid.model

class TopPageRepository {
    private val placeData: List<List<String>> = initPlaceList()
    private val placeNameRandomView: List<String> = initPlaceNameRandom()

    fun getPlaceNameTop(): List<List<String>> {
        return placeData
    }

    fun getRandomViewText(): List<String> {
        return placeNameRandomView
    }

    private fun initPlaceList(): List<List<String>> {
        return listOf(
            listOf("hoge", "hoge1", "hoge", "hoge1"),
            listOf("hoge", "hoge1", "hoge", "hoge1"),
            listOf("hoge", "hoge1", "hoge", "hoge1"),
            listOf("hoge", "hoge1", "hoge", "hoge1"),
        )
    }

    private fun initPlaceNameRandom(): List<String> {
        return listOf("random01", "random02", "random03", "random04", "random05")
    }



}