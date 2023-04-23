package ca.josue.homefinder.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ca.josue.homefinder.domain.models.BottomMenu
import ca.josue.homefinder.navigation.Screen
import ca.josue.homefinder.presentation.details.DetailsScreenRoute
import ca.josue.homefinder.presentation.details.DetailsViewModel
import ca.josue.homefinder.presentation.house.HomeScreenRoute
import ca.josue.homefinder.presentation.house.HomeViewModel
import ca.josue.homefinder.ui.theme.dimensions
import ca.josue.homefinder.utils.Constants

/**
 * created by Josue Lubaki
 * date : 2023-04-17
 * version : 1.0.0
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass,
    homeViewModel: HomeViewModel,
    detailsViewModel: DetailsViewModel
) {

    val menus = listOf(
        BottomMenu.Home,
        BottomMenu.Profile,
    )

    Scaffold(
        content = {
            if (windowSize == WindowWidthSizeClass.Expanded) {
                Row(modifier = Modifier.padding(it)) {
                    NavigationRails(
                        modifier = Modifier.padding(top = 16.dp),
                        navController = navController,
                        menus = menus
                    )
                    Content(
                        navController = navController,
                        windowSize = windowSize,
                        homeViewModel = homeViewModel,
                        detailsViewModel = detailsViewModel
                    )
                }
            } else {
                Content(
                    navController = navController,
                    padding = it,
                    windowSize = windowSize,
                    homeViewModel = homeViewModel,
                    detailsViewModel = detailsViewModel
                )
            }
        },
        bottomBar = {
            if (windowSize != WindowWidthSizeClass.Expanded) {
                Column {
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onSurface,
                        thickness = 1.dp
                    )
                    BottomBarNavigation(
                        navController = navController,
                        menus = menus,
                    )
                }
            }
        },
    )
}

@Composable
fun Content(
    padding: PaddingValues = PaddingValues(0.dp),
    navController: NavHostController,
    windowSize: WindowWidthSizeClass,
    homeViewModel: HomeViewModel,
    detailsViewModel: DetailsViewModel
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        val onBackPressed = {
            navController.popBackStack()
        }

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeScreenRoute(
                    windowSize = windowSize,
                    viewModel = homeViewModel,
                    onHouseClicked = { houseUuid ->
                        navController.navigate(Screen.Details.passHeroId(houseUuid))
                    }
                )
            }

            composable(
                route = Screen.Details.route,
                arguments = listOf(navArgument(Constants.DETAILS_ARGUMENT_KEY) {
                    type = NavType.IntType
                })
            ) {
                val houseUuid = it.arguments?.getInt(Constants.DETAILS_ARGUMENT_KEY) ?: -1
                DetailsScreenRoute(
                    houseUuid = houseUuid,
                    viewModel = detailsViewModel,
                    onBackPressed = onBackPressed
                )
            }
        }
    }
}

@Composable
fun BottomBarNavigation(
    menus: List<BottomMenu>,
    navController: NavHostController,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = MaterialTheme.dimensions.default,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        menus.forEach { menu ->
            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.testTag(menu.route),
                        imageVector = menu.icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = menu.resourceId),
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                selected = currentDestination?.route == menu.route,
                onClick = {
                    navController.navigate(menu.route) {
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                )
            )
        }
    }
}

@Composable
fun NavigationRails(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    menus: List<BottomMenu>
) {
    NavigationRail(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        menus.forEach { menu ->
            NavigationRailItem(
                modifier = Modifier.padding(bottom = MaterialTheme.dimensions.xLarge),
                icon = {
                    Icon(
                        imageVector = menu.icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = menu.resourceId),
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                selected = currentDestination?.route == menu.route,
                onClick = {
                    navController.navigate(menu.route) {
                        launchSingleTop = true
                    }
                },
                colors = NavigationRailItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                )
            )
        }
    }
}