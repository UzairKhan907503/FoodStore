package com.sampleapp.core.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Created by Uzair Nawaz on 24-Sep-21.
 */
abstract class BaseViewModel : ViewModel() {

    private val baseEventsChannel = Channel<BaseEvent>()
    val baseEvents = baseEventsChannel.receiveAsFlow().distinctUntilChanged()

    suspend fun sendError(error: String?) {
        baseEventsChannel.send(BaseEvent.EventError(error ?: ""))
    }

    sealed class BaseEvent {
        data class EventError(val msg: String = "") : BaseEvent()
    }
}