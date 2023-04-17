package ca.josue.homefinder.presentation.house

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ca.josue.homefinder.data.models.house.HouseType
import ca.josue.homefinder.domain.models.Address
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.Owner
import ca.josue.homefinder.presentation.components.CardHouse
import ca.josue.homefinder.ui.theme.HomeFinderTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */
@Composable
fun HomeScreenRoute(
    windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    viewModel: HomeViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val allHouses = remember { mutableStateOf<Flow<PagingData<House>>>(flowOf()) }
    val errorMessage = rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = true) {
        viewModel.onGetAllHouses()
        Timber.d("44 - HomeScreenRoute : launched effect - true")
    }

    LaunchedEffect(key1 = state) {
        when (state) {
            is HomeState.Success -> {
                allHouses.value = (state as HomeState.Success).houses
                Timber.d("51 - HomeScreenRoute - success : ${allHouses.value.count()}")
            }

            is HomeState.Error -> {
                errorMessage.value = (state as HomeState.Error).exception.message
                Timber.d("53 - HomeScreenRoute - error : $errorMessage")
            }

            else -> Unit
        }
    }

    if (windowSize == WindowWidthSizeClass.Expanded) {
        LargeHomeScreen(
            list = allHouses.value.collectAsLazyPagingItems()
        )
    } else {
        HomeScreen(
            list = allHouses.value.collectAsLazyPagingItems()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LargeHomeScreen(list: LazyPagingItems<House>) {
    // grid of 3 columns
    // LazyVerticalStaggeredGrid
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(bottom = 12.dp),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        items(
            items = list.itemSnapshotList.items,
            key = { house -> house.id }
        ) { house ->
            CardHouse(
                house = house,
                isLiked = false,
                windowSize = WindowWidthSizeClass.Expanded
            )
        }
    }
}

@Composable
fun HomeScreen(
    list: LazyPagingItems<House>,
) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 4.dp),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(
            items = list,
            key = { house -> house.id }
        ) { house ->
            if (house != null) {
                CardHouse(
                    house = house,
                    isLiked = false,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeFinderTheme {
        HomeScreen(
            list = flowOf(
                PagingData.from(
                    listOf(
                        House(
                            id = "1",
                            uuid = 1,
                            description = "Condo à vendre - situé dans un quartier tranquille, et il est approximité de tous les services essentiels." +
                                    " Il est situé à 5 minutes de l'autoroute 40, à 10 minutes du centre-ville de Trois-Rivières et à 15 minutes de l'université UQTR.",
                            images = listOf(
                                "https://images.unsplash.com/photo-1570129477492-45c003edd2be?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80",
                                "https://images.unsplash.com/photo-1560184897-ae75f418493e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
                            ),
                            price = 310500,
                            address = Address(
                                id = "1",
                                number = "1A",
                                street = "Boulevard des Forges",
                                city = "Trois-Rivières",
                                province = "Québec",
                                postalCode = "G8T 8T8",
                                country = "Canada",
                            ),
                            bedrooms = 1,
                            bathrooms = 3,
                            area = 1207,
                            type = HouseType.CONDO.name,
                            yearBuilt = 2004,
                            pool = true,
                            owner = Owner(
                                id = "1",
                                username = "josue-lubaki",
                                firstName = "Josue",
                                lastName = "Lubaki",
                                email = "josuelubaki@gmail.com",
                                phone = "819-123-4567",
                            ),
                        ),
                        House(
                            id = "2",
                            uuid = 2,
                            description = "Condo à vendre - situé dans un quartier tranquille, et il est approximité de tous les services essentiels." +
                                    " Il est situé à 5 minutes de l'autoroute 40, à 10 minutes du centre-ville de Trois-Rivières et à 15 minutes de l'université UQTR.",
                            images = listOf(
                                "https://images.unsplash.com/photo-1570129477492-45c003edd2be?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80",
                                "https://images.unsplash.com/photo-1560184897-ae75f418493e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
                            ),
                            price = 310500,
                            address = Address(
                                id = "2",
                                number = "1A",
                                street = "Boulevard des Forges",
                                city = "Trois-Rivières",
                                province = "Québec",
                                postalCode = "G8T 8T8",
                                country = "Canada",
                            ),
                            bedrooms = 1,
                            bathrooms = 3,
                            area = 1207,
                            type = HouseType.CONDO.name,
                            yearBuilt = 2004,
                            pool = true,
                            owner = Owner(
                                id = "2",
                                username = "josue-lubaki",
                                firstName = "Josue",
                                lastName = "Lubaki",
                                email = "josuelubaki@gmail.com",
                                phone = "819-123-4567",
                            ),
                        ),
                    )
                )
            ).collectAsLazyPagingItems()
        )
    }
}
