package com.example.mobilemorozova

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnboardingPageFragment : Fragment(R.layout.fragment_onboarding_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = requireArguments()
        view.findViewById<ImageView>(R.id.ivPage).setImageResource(args.getInt(ARG_IMAGE))
        view.findViewById<TextView>(R.id.tvTitle).text = args.getString(ARG_TITLE)
        view.findViewById<TextView>(R.id.tvDescription).text = args.getString(ARG_DESC)
    }

    companion object {
        private const val ARG_IMAGE = "image"
        private const val ARG_TITLE = "title"
        private const val ARG_DESC = "desc"

        fun newInstance(page: OnboardingPage) = OnboardingPageFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_IMAGE, page.image)
                putString(ARG_TITLE, page.title)
                putString(ARG_DESC, page.description)
            }
        }
    }
}