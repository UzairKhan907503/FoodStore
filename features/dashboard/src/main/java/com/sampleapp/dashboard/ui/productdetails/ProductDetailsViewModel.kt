package com.sampleapp.dashboard.ui.productdetails

import androidx.lifecycle.SavedStateHandle
import com.sampleapp.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    state: SavedStateHandle
) : BaseViewModel() {
    private val _state = MutableStateFlow<ProductDetailsStates>(ProductDetailsStates.Idle)
    val state = _state.asStateFlow()
}