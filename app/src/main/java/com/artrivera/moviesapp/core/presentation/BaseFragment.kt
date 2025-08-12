package com.artrivera.moviesapp.core.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment

abstract class BaseFragment() : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Let the system handle insets for fragments by default
        ViewCompat.setOnApplyWindowInsetsListener(view, null)
        view.fitsSystemWindows = true
    }
}