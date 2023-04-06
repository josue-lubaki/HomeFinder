package ca.josue.homefinder.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ca.josue.homefinder.presentation.splash.SplashScreen
import ca.josue.homefinder.presentation.splash.SplashViewModel
import ca.josue.homefinder.presentation.welcome.WelcomeScreen
import ca.josue.homefinder.presentation.welcome.WelcomeViewModel

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

fun NavGraphBuilder.rootNavigationGraph(
    navController: NavHostController,
    windowSize: WindowWidthSizeClass,
    welcomeViewModel: WelcomeViewModel,
    splashViewModel: SplashViewModel
) {
    navigation(
        route = Graph.ROOT,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route){
            SplashScreen(
                windowSize = windowSize,
                viewModel = splashViewModel,
                onNavigateToRoute = {
                    navController.popBackStack()
                    navController.navigate(it)
                }
            )
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                windowSize = windowSize,
                onFinishedOnBoarding = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTH)
                    welcomeViewModel.saveOnBoardingState(completed = true)
                }
            )
        }
    }
}