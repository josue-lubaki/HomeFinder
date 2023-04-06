package ca.josue.homefinder.navigation

import ca.josue.homefinder.utils.Constants.DETAILS_ARGUMENT_KEY

sealed class Screen(val route : String){
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgot_password")
    object ResetPassword : Screen("reset_password")
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Details : Screen("details/{$DETAILS_ARGUMENT_KEY}"){
        fun passHeroId(houseId : Int) = "details/$houseId"
    }
    object Search : Screen("search")
}