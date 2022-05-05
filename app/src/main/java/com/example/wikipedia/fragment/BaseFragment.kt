package com.example.wikipedia.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.wikipedia.R

open class BaseFragment:Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        overridePendingTransitionUp()
    }

    override fun onDetach() {
        super.onDetach()
        overridePendingTransitionDown()
    }

    //Down
    private fun overridePendingTransitionDown() {
        activity?.overridePendingTransition(R.anim.slide_from_down, R.anim.slide_to_down)

    }

    //Up
    private fun overridePendingTransitionUp() {
        activity?.overridePendingTransition(R.anim.slide_from_up, R.anim.slide_to_up)


    }
}