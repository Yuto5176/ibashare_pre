package com.swift2021.ibashareandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swift2021.ibashareandroid.model.PlaceListData
import com.swift2021.ibashareandroid.model.SeeMorePageRepository

class SeeMorePageViewModel(application: Application):AndroidViewModel(application) {
    private val seeMorePageRepository: SeeMorePageRepository = SeeMorePageRepository(application)

    val nameList: LiveData<List<String>> = MutableLiveData(seeMorePageRepository.getSeeMoreName())

    val placeList: LiveData<PlaceListData> = MutableLiveData(seeMorePageRepository.getPlaceListData())
}