package ca.josue.homefinder.navigation

import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ca.josue.homefinder.presentation.AppScreen
import ca.josue.homefinder.presentation.details.DetailsViewModel
import ca.josue.homefinder.presentation.house.HomeViewModel
import ca.josue.homefinder.presentation.login.LoginViewModel
import ca.josue.homefinder.presentation.splash.SplashViewModel
import ca.josue.homefinder.presentation.welcome.WelcomeViewModel

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    windowSize: WindowWidthSizeClass,
    videoUri : Uri,
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    detailsViewModel: DetailsViewModel = hiltViewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = Graph.ROOT
    ) {
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
            videoUri = videoUri,
            onNavigateToRegister = {
                navController.navigate(Screen.Register.route)
            },
            onNavigateToHome = {
                navController.popBackStack()
                navController.navigate(Graph.MAIN)
            }
        )

        composable(route = Graph.MAIN) {
            AppScreen(
                windowSize = windowSize,
                homeViewModel = homeViewModel,
                detailsViewModel = detailsViewModel,
            )
        }
    }
}