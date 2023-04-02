package ca.josue.homefinder.presentation.house

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import timber.log.Timber

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHouses = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    // TODO : Need Scaffold
    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(
            items = allHouses,
            key = { house -> house.id }
        ){house ->
            Text(text = house?.uuid.toString() + " - " + house?.address?.city)
            Timber.d("36 - HomeScreen : $house")
            // TODO : 21 - Create a HouseItem composable
        }
    }

}