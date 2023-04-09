package ca.josue.homefinder.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ca.josue.homefinder.navigation.Graph
import ca.josue.homefinder.navigation.Screen
import ca.josue.homefinder.utils.Constants.LOTTIE_URL
import ca.josue.homefinder.utils.Constants.MAX_SLEEP_TIME
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

@Composable
fun SplashScreen(
    windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    viewModel: SplashViewModel,
    onNavigateToRoute: (String) -> Unit
) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(LOTTIE_URL))
    val onBoardingState by viewModel.onBoardingState.collectAsState()

    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO) {
            sleep(MAX_SLEEP_TIME)
        }

        when (onBoardingState) {
            is SplashState.Completed -> {
                onNavigateToRoute(Graph.AUTH)
            }
            is SplashState.OnBoardingNotCompleted -> {
                onNavigateToRoute(Screen.Welcome.route)
            }
        }
    }

    Splash(composition = composition)
}

@Composable
fun Splash(composition : LottieComposition?) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}