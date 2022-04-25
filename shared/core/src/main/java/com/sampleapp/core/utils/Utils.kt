package com.sampleapp.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.R.animator.*
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.sampleapp.core.R
import com.sampleapp.remote.utils.ApiUtils

fun String.getCompleteUrl() = ApiUtils.BASE_URL + this

fun getProgressDrawable(context: Context): Drawable {
    return  CircularProgressDrawable(context).apply {
        strokeWidth = 15f
        centerRadius = 40f
        start()
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun navigate(
    navController: NavController,
    navDirections: NavDirections,
    navBuilder: NavOptions.Builder? = null
) {
    val navOptions = navBuilder ?: NavOptions.Builder()

    navController.navigate(
        navDirections,
        navOptions.addAnimations().build()
    )
}

fun NavOptions.Builder.addAnimations() = this.apply {
    setEnterAnim(fragment_open_enter)
        .setExitAnim(fragment_fade_exit)
        .setPopEnterAnim(fragment_close_enter)
        .setPopExitAnim(fragment_fade_exit)
        .build()
}

fun ImageView.loadImage(url : String){
    load(
        url.getCompleteUrl()
    ){
        placeholder(getProgressDrawable(context))
        error(R.drawable.error_loading)
    }
}