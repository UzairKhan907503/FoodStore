package com.sampleapp.core.utils

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

fun Fragment.collectWhenStarted(action: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            action.invoke()
        }
    }
}

fun Fragment.showError(message: String) {
    AlertDialog.Builder(requireContext())
        .setTitle("Error")
        .setMessage(message)
        .setPositiveButton("Ok") { _, _ ->

        }
        .setNegativeButton("Cancel") { _, _ ->

        }
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show()
}