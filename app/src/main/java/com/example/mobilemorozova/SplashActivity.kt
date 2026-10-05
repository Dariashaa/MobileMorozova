package com.example.mobilemorozova   // ← оставь свою строку package

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlin.jvm.java

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
            !NetworkUtils.hasInternet(this) -> NoInternetActivity::class.java
            !isOnboardingDone()             -> MainActivity::class.java
            !isTokenValid()                 -> MainActivity::class.java
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