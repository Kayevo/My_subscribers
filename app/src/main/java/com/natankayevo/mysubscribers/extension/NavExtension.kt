package com.natankayevo.mysubscribers.extension

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.natankayevo.mysubscribers.R

private val defaultTransitionsOpt = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

fun NavController.navigateWithTransitions(
    destinationId: Int,
    animation: NavOptions = defaultTransitionsOpt
){
    this.navigate(destinationId, null, animation)
}

fun NavController.navigateWithTransitions(
    destinationId: NavDirections,
    animation: NavOptions = defaultTransitionsOpt
){
    this.navigate(destinationId, animation)
}