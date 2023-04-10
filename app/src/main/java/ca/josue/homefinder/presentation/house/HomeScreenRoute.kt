package ca.josue.homefinder.presentation.house

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.presentation.components.CardHouse
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
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val allHouses = remember { mutableStateOf<Flow<PagingData<House>>>(flowOf())}
    val errorMessage = rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = true){
        viewModel.onGetAllHouses()
        Timber.d("44 - HomeScreenRoute : launched effect - true")
    }

    LaunchedEffect(key1 = state){
        when(state){
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

    HomeScreen(
        list = allHouses.value.collectAsLazyPagingItems()
    )
}

@Composable
fun HomeScreen(
    list: LazyPagingItems<House>,
) {
    // TODO : Need Scaffold
    LazyColumn(
        modifier = Modifier.padding(bottom = 12.dp),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
        items(
            items = list,
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
