package com.sampleapp.core.ui.activityviewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MainActivityViewModel : ViewModel() {

    var activityIntents = Channel<LaunchActivityEvents>()
    val event get() = activityIntents.receiveAsFlow()
}
