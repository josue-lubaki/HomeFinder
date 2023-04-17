package ca.josue.homefinder.domain.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import ca.josue.homefinder.R

sealed class BottomMenu(
    val icon: ImageVector,
    @StringRes val resourceId: Int,
    val route: String,
) {
    object Home : BottomMenu(
        icon = Icons.Default.Home,
        resourceId = R.string.menu_home,
        route = "home"
    )

    object Search : BottomMenu(
        icon = Icons.Default.Search,
        resourceId = R.string.menu_search,
        route = "search"
    )

    object Profile : BottomMenu(
        icon = Icons.Default.Person,
        resourceId = R.string.menu_profile,
        route = "profile"
    )
}
