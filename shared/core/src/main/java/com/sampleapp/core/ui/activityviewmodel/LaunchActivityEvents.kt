package com.sampleapp.core.ui.activityviewmodel

import androidx.navigation.NavOptions
import com.sampleapp.core.navigationflows.MainActivityNavigationFlow

sealed class LaunchActivityEvents {
    data class NavigateToFlow(
        val destination: MainActivityNavigationFlow,
        val navOptions: NavOptions.Builder?
    ) : LaunchActivityEvents()
}