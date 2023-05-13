package ca.josue.homefinder.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ca.josue.homefinder.presentation.login.LoginScreenRoute
import ca.josue.homefinder.presentation.login.LoginViewModel

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

fun NavGraphBuilder.authNavigationGraph(
    windowSize: WindowWidthSizeClass,
    loginViewModel : LoginViewModel,
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    navigation(
        route = Graph.AUTH,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreenRoute(
                windowSize = windowSize,
                viewModel = loginViewModel,
                onNavigateToRegister = onNavigateToRegister,
                onNavigateToHome = onNavigateToHome
            )
        }

        composable(Screen.Register.route) {
//            RegisterScreen()
        }

        composable(Screen.ForgotPassword.route) {
//            ForgotPasswordScreen()
        }

        composable(Screen.ResetPassword.route) {
//            ResetPasswordScreen()
        }
    }
}