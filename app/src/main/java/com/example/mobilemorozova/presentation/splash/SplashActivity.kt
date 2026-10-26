package com.example.mobilemorozova.presentation.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mobilemorozova.presentation.login.LoginActivity
import com.example.mobilemorozova.presentation.main.MainActivity
import com.example.mobilemorozova.data.NetworkRepositoryImpl
import com.example.mobilemorozova.presentation.nointernet.NoInternetActivity
import com.example.mobilemorozova.presentation.onboarding.OnboardingActivity
import com.example.mobilemorozova.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({ navigateNext() }, 2500)
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun navigateNext() {
        val target = when {
            !NetworkRepositoryImpl.hasInternet(this) -> NoInternetActivity::class.java
            !isOnboardingDone()             -> OnboardingActivity::class.java
            !isTokenValid()                 -> LoginActivity::class.java
            else                            -> MainActivity::class.java
        }
        startActivity(Intent(this, target))
        finish()
    }

    private fun isOnboardingDone(): Boolean =
        getSharedPreferences("prefs", MODE_PRIVATE)
            .getBoolean("onboarding_done", false)


    private fun isTokenValid(): Boolean = false
}