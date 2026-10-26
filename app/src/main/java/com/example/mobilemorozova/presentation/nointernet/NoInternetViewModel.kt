package com.example.mobilemorozova.presentation.nointernet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobilemorozova.domain.NetworkRepository

class NoInternetViewModel(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean> = _isConnected

    fun retry() {
        _isConnected.value = networkRepository.hasInternet()
    }
}