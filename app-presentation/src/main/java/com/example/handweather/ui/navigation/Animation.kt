package com.example.handweather.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

//For some reason I was getting a weird runtime error if I placed this component on the components
//module. I tried looking into it and it has something to do with the gradle plugin version, but I
//don't have time to deal with it right now, so this particular component will be placed on the
//app-presentation module itself.

private const val DURATION = 400
private const val OFFSET = 1000

private val animationSpec = tween<IntOffset>(
    durationMillis = DURATION,
    easing = FastOutSlowInEasing,
)

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.slideComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { OFFSET }, animationSpec = animationSpec) + fadeIn()
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX  = { -OFFSET }, animationSpec = animationSpec) + fadeOut()
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -OFFSET }, animationSpec = animationSpec) + fadeIn()
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX  = { OFFSET }, animationSpec = animationSpec) + fadeOut()
        },
        content = content
    )
}