package com.sampleapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.sampleapp.core.navigationflows.MainActivityNavigationFlow

class MainActivityNavigationImpl : MainActivityNavigation {
    override fun navigateToFlow(
        navController: NavController,
        flowMainActivity: MainActivityNavigationFlow,
        navOptions: NavOptions.Builder?
    ) {
        when (flowMainActivity) {
            is MainActivityNavigationFlow.Dashboard -> {
                navOptions?.run {
                    navController.navigate(
                        MainActivityNavigationDirections.actionToDashboard(),
                        build()
                    )
                } ?: navController.navigate(MainActivityNavigationDirections.actionToDashboard())
            }
        }

    }
}