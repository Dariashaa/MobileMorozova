package com.example.mobilemorozova

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NoInternetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)

        findViewById<Button>(R.id.btnRetry).setOnClickListener {
            if (NetworkUtils.hasInternet(this)) {
                // Успех: запускаем обычный сценарий старта заново
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            } else {
                // Ошибка: остаёмся на экране
                Toast.makeText(this, "Нет подключения к интернету", Toast.LENGTH_SHORT).show()
            }
        }
    }
}