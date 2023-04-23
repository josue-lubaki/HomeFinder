package ca.josue.homefinder.presentation.house

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ca.josue.homefinder.R
import ca.josue.homefinder.data.models.house.HouseType
import ca.josue.homefinder.domain.models.Address
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.Owner
import ca.josue.homefinder.presentation.components.CardHouse
import ca.josue.homefinder.ui.theme.HomeFinderTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */
@Composable
fun HomeScreenRoute(
    windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    viewModel: HomeViewModel,
    onHouseClicked: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val allHouses = remember { mutableStateOf<Flow<PagingData<House>>>(flowOf()) }
    val errorMessage = rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = Unit) {
        viewModel.onGetAllHouses()
    }

    LaunchedEffect(key1 = state) {
        when (state) {
            is HomeState.Success -> {
                allHouses.value = (state as HomeState.Success).houses
            }

            is HomeState.Error -> {
                errorMessage.value = (state as HomeState.Error).exception.message
            }

            else -> Unit
        }
    }

    if (windowSize == WindowWidthSizeClass.Expanded) {
        LargeHomeScreen(
            list = allHouses.value.collectAsLazyPagingItems(),
            onHouseClicked = onHouseClicked
        )
    } else {
        SmallHomeScreen(
            list = allHouses.value.collectAsLazyPagingItems(),
            onHouseClicked = onHouseClicked
        )
    }
}

@Composable
fun LargeHomeScreen(
    list: LazyPagingItems<House>,
    onHouseClicked: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    val lazyGridState = rememberLazyGridState()
    Column {
        HeadCard(number = list.itemSnapshotList.size)
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = lazyGridState,
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
                    windowSize = WindowWidthSizeClass.Expanded,
                    onHouseClicked = onHouseClicked
                )
            }
        }
    }
}

@Composable
fun SmallHomeScreen(
    list: LazyPagingItems<House>,
    onHouseClicked: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        HeadCard(number = list.itemSnapshotList.size)
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .padding(bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ){
            list.itemSnapshotList.forEach { house ->
                if (house != null) {
                    CardHouse(
                        house = house,
                        isLiked = false,
                        onHouseClicked = onHouseClicked,
                    )
                }
            }
        }
    }
}

@Composable
fun HeadCard(number: Int) {
    Box(modifier = Modifier.fillMaxWidth()) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = stringResource(R.string.hello),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = when (number) {
                    0 -> stringResource(R.string.no_house_found)
                    1 -> stringResource(R.string.house_found)
                    else -> stringResource(R.string.houses_found, number)
                },
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeadPreview() {
    HomeFinderTheme {
        HeadCard(number = 4)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeFinderTheme {
        SmallHomeScreen(
            onHouseClicked = {},
            list = flowOf(
                PagingData.from(
                    listOf(
                        House(
                            id = "1",
                            uuid = 1,
                            description = "Condo à vendre - situé dans un quartier tranquille, et il est approximité de tous les services essentiels." +
                                    " Il est situé à 5 minutes de l'autoroute 40, à 10 minutes du centre-ville de Trois-Rivières et à 15 minutes de l'université UQTR.",
                            images = listOf(),
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
                            images = listOf(),
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
