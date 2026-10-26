package com.example.mobilemorozova.domain

interface OnboardingRepository {
    fun isOnboardingDone(): Boolean
    fun setOnboardingDone()
}