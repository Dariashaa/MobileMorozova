package com.example.mobilemorozova.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilemorozova.domain.NetworkRepository
import com.example.mobilemorozova.domain.OnboardingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class SplashDestination { NO_INTERNET, ONBOARDING, LOGIN, MAIN }

class SplashViewModel(
    private val networkRepository: NetworkRepository,
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    private val _destination = MutableLiveData<SplashDestination>()
    val destination: LiveData<SplashDestination> = _destination

    init {
        viewModelScope.launch {
            delay(SPLASH_DELAY_MS)
            _destination.value = when {
                !networkRepository.hasInternet() -> SplashDestination.NO_INTERNET
                !onboardingRepository.isOnboardingDone() -> SplashDestination.ONBOARDING
                !isTokenValid() -> SplashDestination.LOGIN
                else -> SplashDestination.MAIN
            }
        }
    }

    // Заглушка: токена пока нет, появится вместе с авторизацией
    private fun isTokenValid(): Boolean = false

    companion object {
        private const val SPLASH_DELAY_MS = 2500L
    }
}