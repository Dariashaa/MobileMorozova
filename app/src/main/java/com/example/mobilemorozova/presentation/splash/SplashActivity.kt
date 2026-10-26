package com.example.mobilemorozova.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mobilemorozova.R
import com.example.mobilemorozova.presentation.ViewModelFactory
import com.example.mobilemorozova.presentation.login.LoginActivity
import com.example.mobilemorozova.presentation.main.MainActivity
import com.example.mobilemorozova.presentation.nointernet.NoInternetActivity
import com.example.mobilemorozova.presentation.onboarding.OnboardingActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.destination.observe(this) { destination ->
            val target = when (destination) {
                SplashDestination.NO_INTERNET -> NoInternetActivity::class.java
                SplashDestination.ONBOARDING -> OnboardingActivity::class.java
                SplashDestination.LOGIN -> LoginActivity::class.java
                SplashDestination.MAIN -> MainActivity::class.java
            }
            startActivity(Intent(this, target))
            finish()
        }
    }
}