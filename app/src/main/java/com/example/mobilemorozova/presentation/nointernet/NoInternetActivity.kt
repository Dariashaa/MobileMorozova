package com.example.mobilemorozova.presentation.nointernet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilemorozova.R
import com.example.mobilemorozova.presentation.ViewModelFactory
import com.example.mobilemorozova.presentation.splash.SplashActivity

class NoInternetActivity : AppCompatActivity() {

    private val viewModel: NoInternetViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)

        findViewById<Button>(R.id.btnRetry).setOnClickListener { viewModel.retry() }

        viewModel.isConnected.observe(this) { connected ->
            if (connected) {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Нет подключения к интернету", Toast.LENGTH_SHORT).show()
            }
        }
    }
}