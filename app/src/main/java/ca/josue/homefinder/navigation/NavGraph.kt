package ca.josue.homefinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.josue.homefinder.presentation.house.HomeScreen
import ca.josue.homefinder.utils.Constants.DETAILS_ARGUMENT_KEY

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

@Composable
fun SetupNavGraph(navController : NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(Screen.Splash.route){
//            SplashScreen(navController = navController)
        }
        composable(Screen.Welcome.route){
//            WelcomeScreen(navController = navController)
        }
        composable(Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) { type = NavType.IntType })
        ) {
//            DetailsScreen(navController = navController)
        }
        composable(Screen.Search.route){
//            SearchScreen(navController = navController)
        }
    }
}