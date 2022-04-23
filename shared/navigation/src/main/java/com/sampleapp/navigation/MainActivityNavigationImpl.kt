package com.sampleapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.sampleapp.core.navigationflows.MainActivityNavigationFlow
import com.sampleapp.core.utils.navigate

class MainActivityNavigationImpl : MainActivityNavigation {
    override fun navigateToFlow(
        navController: NavController,
        flowMainActivity: MainActivityNavigationFlow,
        navOptions: NavOptions.Builder?
    ) {
        when (flowMainActivity) {
            is MainActivityNavigationFlow.Dashboard -> {
                    navigate(
                        navController,
                        MainActivityNavigationDirections.actionToDashboard(),
                        navOptions
                    )
                }
            }
        }

    }
