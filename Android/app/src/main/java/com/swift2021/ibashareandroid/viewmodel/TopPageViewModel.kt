package com.swift2021.ibashareandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swift2021.ibashareandroid.model.TopPageRepository

class TopPageViewModel(application: Application): AndroidViewModel(application) {
    private val topPageRepository: TopPageRepository = TopPageRepository()

    val placeNameListTop01: LiveData<List<List<String>>> = MutableLiveData(topPageRepository.getPlaceNameTop())

    val randomViewText: LiveData<List<String>> = MutableLiveData(topPageRepository.getRandomViewText())
}