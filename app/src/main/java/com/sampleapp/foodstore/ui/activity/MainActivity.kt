package com.sampleapp.foodstore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sampleapp.core.ui.activityviewmodel.LaunchActivityEvents
import com.sampleapp.core.ui.activityviewmodel.MainActivityViewModel
import com.sampleapp.foodstore.R
import com.sampleapp.navigation.MainActivityNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mViewModel by viewModels<MainActivityViewModel>()

    @Inject
    lateinit var launchActivityNavigation: MainActivityNavigation
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.event.collect { events ->
                    when (events) {
                        is LaunchActivityEvents.NavigateToFlow -> {
                            launchActivityNavigation.navigateToFlow(
                                navController, events.destination, events.navOptions
                            )
                        }
                    }
                }
            }
        }
    }
}