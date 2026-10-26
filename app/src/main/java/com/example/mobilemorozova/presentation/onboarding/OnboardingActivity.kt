package com.example.mobilemorozova.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.mobilemorozova.presentation.login.LoginActivity
import com.example.mobilemorozova.R
import com.google.android.material.button.MaterialButton

class OnboardingActivity : AppCompatActivity() {

    private val pages = listOf(
        OnboardingPage(
            R.drawable.onboarding_1, "Аренда автомобилей",
            "Открой для себя удобный и доступный способ передвижения"
        ),
        OnboardingPage(
            R.drawable.onboarding_2, "Безопасно и удобно",
            "Арендуй автомобиль и наслаждайся его удобством"
        ),
        OnboardingPage(
            R.drawable.onboarding_3, "Лучшие предложения",
            "Выбирай понравившееся среди сотен доступных автомобилей"
        )
    )

    private lateinit var viewPager: ViewPager2
    private lateinit var indicators: LinearLayout
    private lateinit var btnNext: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        indicators = findViewById(R.id.indicators)
        btnNext = findViewById(R.id.btnNext)
        val tvSkip = findViewById<TextView>(R.id.tvSkip)

        viewPager.adapter = OnboardingAdapter(this, pages)
        createIndicators()
        updateUi(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) = updateUi(position)
        })

        btnNext.setOnClickListener {
            if (viewPager.currentItem < pages.lastIndex) {
                viewPager.setCurrentItem(viewPager.currentItem + 1, true)
            } else {
                finishOnboarding()
            }
        }
        tvSkip.setOnClickListener { finishOnboarding() }
    }

    private fun createIndicators() {
        pages.forEach { _ ->
            val dot = ImageView(this)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.marginEnd = (6 * resources.displayMetrics.density).toInt()
            dot.layoutParams = params
            indicators.addView(dot)
        }
    }

    private fun updateUi(position: Int) {
        for (i in 0 until indicators.childCount) {
            (indicators.getChildAt(i) as ImageView).setImageResource(
                if (i == position) R.drawable.indicator_active
                else R.drawable.indicator_inactive
            )
        }
        btnNext.text = if (position == pages.lastIndex) "Поехали" else "Далее"
    }

    private fun finishOnboarding() {
        getSharedPreferences("prefs", MODE_PRIVATE)
            .edit()
            .putBoolean("onboarding_done", true)
            .apply()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}