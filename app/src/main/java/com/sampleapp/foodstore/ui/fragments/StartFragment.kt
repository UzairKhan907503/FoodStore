package com.sampleapp.foodstore.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import com.sampleapp.core.navigationflows.MainActivityNavigationFlow
import com.sampleapp.core.ui.activityviewmodel.LaunchActivityEvents
import com.sampleapp.core.ui.activityviewmodel.MainActivityViewModel
import com.sampleapp.core.utils.collectWhenStarted
import com.sampleapp.foodstore.R

class StartFragment : Fragment(R.layout.fragment_start) {
    private val activityViewModel by activityViewModels<MainActivityViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            navigateToDashboard()
        }, 2000)
    }

    private fun navigateToDashboard() {

        collectWhenStarted {
            activityViewModel.activityIntents.send(
                LaunchActivityEvents.NavigateToFlow(
                    MainActivityNavigationFlow.Dashboard,
                    NavOptions.Builder().setPopUpTo(R.id.start_fragment, true)
                )
            )
        }
    }
}