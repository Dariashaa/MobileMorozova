package com.example.mobilemorozova.data

import android.content.Context
import com.example.mobilemorozova.domain.OnboardingRepository

class OnboardingRepositoryImpl(context: Context) : OnboardingRepository {

    private val prefs = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun isOnboardingDone(): Boolean =
        prefs.getBoolean(KEY_ONBOARDING_DONE, false)

    override fun setOnboardingDone() {
        prefs.edit().putBoolean(KEY_ONBOARDING_DONE, true).apply()
    }

    companion object {
        private const val PREFS_NAME = "prefs"
        private const val KEY_ONBOARDING_DONE = "onboarding_done"
    }
}