package com.example.mobilemorozova.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.example.mobilemorozova.domain.OnboardingRepository

class OnboardingViewModel(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    fun finishOnboarding() {
        onboardingRepository.setOnboardingDone()
    }
}