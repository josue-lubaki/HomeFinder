package ca.josue.homefinder.utils

import androidx.annotation.StringRes
import androidx.compose.ui.unit.dp
import ca.josue.homefinder.R
import ca.josue.homefinder.data.models.house.HouseType

object Constants {
    const val LOTTIE_URL = "https://assets4.lottiefiles.com/packages/lf20_DDeczlewjT.json"
    const val HOME_FINDER_DB = "home_finder_db"
    const val PREFERENCES_NAME = "homefinder_preferences"
    const val PREFERENCES_KEY = "on_boarding_completed"
    const val TOKEN_ACCESS_KEY = "token_access"
    const val ITEMS_PER_PAGE = 5
    const val DETAILS_ARGUMENT_KEY = "houseId"
    const val MAX_SLEEP_TIME = 3500L

    val MIN_SHEET_HEIGHT = 400.dp
    val EXTRA_LARGE_PADDING = 40.dp
    val EXPANDED_RADIUS_LEVEL= 0.dp
    const val MIN_BACKGROUND_IMAGE_HEIGHT = 0.4f

    // headers
    const val HEADER_AUTHORIZATION = "Authorization"
    const val HEADER_BEARER = "Bearer"

    @StringRes
    fun getHomeTypeName(type : String): Int {
        return when (type) {
            HouseType.CONDO.name -> R.string.condo
            HouseType.SINGLE_FAMILY.name -> R.string.single_family
            HouseType.MULTIPLEX.name -> R.string.multiplex
            HouseType.CHALET.name -> R.string.chalet
            else -> R.string.unknown_type
        }
    }
}