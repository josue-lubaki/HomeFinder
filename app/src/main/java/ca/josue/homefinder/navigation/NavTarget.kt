package ca.josue.homefinder.navigation

import ca.josue.homefinder.utils.Constants.DETAILS_ARGUMENT_KEY

sealed class Screen(val route : String){
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{$DETAILS_ARGUMENT_KEY}"){
        fun passHeroId(houseId : Int) = "details_screen/$houseId"
    }
    object Search : Screen("search_screen")
}