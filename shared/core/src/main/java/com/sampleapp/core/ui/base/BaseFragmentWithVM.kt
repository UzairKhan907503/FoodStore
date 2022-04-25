package com.sampleapp.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sampleapp.core.utils.collectWhenStarted
import com.sampleapp.core.utils.showError
import kotlinx.coroutines.flow.collect

abstract class BaseFragmentWithVM<T : ViewDataBinding, V : BaseViewModel> : Fragment() {
    private var _binding: T? = null
    val binding get() = _binding!!

    open fun getBindingVariable(): Int? = null
    abstract fun getViewModel(): V
    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectWhenStarted {
            getViewModel().baseEvents.collect { event ->
                when (event) {
                    is BaseViewModel.BaseEvent.EventError -> {
                        showError(event.msg)
                    }
                }
            }
        }
    }

    private fun showLoader(visible: Boolean) {

    }
}