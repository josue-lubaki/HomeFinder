package ca.josue.homefinder.presentation.house

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ca.josue.homefinder.presentation.components.CardHouse

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */
@Composable
fun HomeScreen(
    windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHouses = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    // TODO : Need Scaffold
    LazyColumn(
        modifier = Modifier.padding(bottom = 12.dp),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
        items(
            items = allHouses,
            key = { house -> house.id }
        ){house ->
            if (house != null) {
                CardHouse(
                    house = house,
                    isLiked = false,
                )
            }
        }
    }

}