package ca.josue.homefinder.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ca.josue.homefinder.presentation.details.DetailsScreen
import ca.josue.homefinder.presentation.house.HomeScreenRoute
import ca.josue.homefinder.utils.Constants

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

fun NavGraphBuilder.mainNavigation(
    navController: NavHostController,
    windowSize: WindowWidthSizeClass,
) {
    navigation(
        route = Graph.MAIN,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreenRoute(
                windowSize = windowSize,
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(Constants.DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            val houseUuid = it.arguments?.getInt(Constants.DETAILS_ARGUMENT_KEY) ?: -1
            DetailsScreen(
                houseUuid = houseUuid,
                windowSize = windowSize,
            )
        }
    }
}