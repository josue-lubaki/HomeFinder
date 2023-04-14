package ca.josue.homefinder.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ca.josue.homefinder.presentation.login.LoginViewModel
import ca.josue.homefinder.presentation.splash.SplashViewModel
import ca.josue.homefinder.presentation.welcome.WelcomeViewModel

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

@Composable
fun SetupNavGraph(
    navController : NavHostController,
    windowSize : WindowWidthSizeClass,
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel(),
    loginViewModel : LoginViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Graph.ROOT
    ){
        rootNavigationGraph(
            navController = navController,
            windowSize = windowSize,
            welcomeViewModel = welcomeViewModel,
            splashViewModel = splashViewModel
        )

        authNavigationGraph(
            navController = navController,
            windowSize = windowSize,
            loginViewModel = loginViewModel,
            onNavigateToRegister = {
                navController.navigate(Screen.Register.route)
            },
            onNavigateToHome = {
                navController.popBackStack()
                navController.navigate(Graph.MAIN)
            }
        )

        mainNavigation(
            navController = navController,
            windowSize = windowSize,
        )
    }
}