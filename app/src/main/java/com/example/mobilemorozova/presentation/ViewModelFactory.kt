package com.example.mobilemorozova.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobilemorozova.data.NetworkRepositoryImpl
import com.example.mobilemorozova.data.OnboardingRepositoryImpl
import com.example.mobilemorozova.presentation.nointernet.NoInternetViewModel
import com.example.mobilemorozova.presentation.onboarding.OnboardingViewModel
import com.example.mobilemorozova.presentation.splash.SplashViewModel

class ViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val networkRepository = NetworkRepositoryImpl(context)
    private val onboardingRepository = OnboardingRepositoryImpl(context)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(SplashViewModel::class.java) ->
            SplashViewModel(networkRepository, onboardingRepository)
        modelClass.isAssignableFrom(OnboardingViewModel::class.java) ->
            OnboardingViewModel(onboardingRepository)
        modelClass.isAssignableFrom(NoInternetViewModel::class.java) ->
            NoInternetViewModel(networkRepository)
        else -> throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
    } as T
}