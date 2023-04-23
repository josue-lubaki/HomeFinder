package ca.josue.homefinder.presentation.details

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ca.josue.homefinder.data.models.house.HouseType
import ca.josue.homefinder.domain.models.Address
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.Owner
import ca.josue.homefinder.presentation.components.BackgroundContent
import ca.josue.homefinder.presentation.components.BottomSheetContent
import ca.josue.homefinder.ui.theme.HomeFinderTheme
import ca.josue.homefinder.ui.theme.dimensions
import ca.josue.homefinder.utils.Constants.EXPANDED_RADIUS_LEVEL
import ca.josue.homefinder.utils.Constants.EXTRA_LARGE_PADDING
import ca.josue.homefinder.utils.Constants.MIN_SHEET_HEIGHT

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

@Composable
fun DetailsScreenRoute(
    houseUuid : Int,
    viewModel: DetailsViewModel,
    onBackPressed: () -> Boolean
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val house = remember { mutableStateOf<House?>(null) }
    val selectedHouse = house.value

    LaunchedEffect(key1 = houseUuid){
        viewModel.getHouseDetails(houseUuid)
    }

    LaunchedEffect(key1 = state){
        when (state) {
            is DetailState.Success -> house.value = (state as DetailState.Success).house
            else -> Unit
        }
    }

    DetailsScreen(
        selectedHouse = selectedHouse,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    selectedHouse: House?,
    onBackPressed: () -> Boolean
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessHigh
            )
        )
    )

    val radiusAnim by animateDpAsState(
        targetValue =
        if(scaffoldState.bottomSheetState.isExpanded)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL,
        label = "bottom_sheet_radius_anim"
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        backgroundColor = MaterialTheme.colorScheme.surface,
        sheetShape = RoundedCornerShape(topStart = radiusAnim, topEnd = radiusAnim),
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxSize(.75f)
                    .padding(MaterialTheme.dimensions.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                selectedHouse?.let {
                    BottomSheetContent(selectedHouse = it)
                }
            }
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            selectedHouse?.let { house ->
                BackgroundContent(
                    houseImageUrl = house.images.first(),
                    onCloseClicked = onBackPressed,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview() {
    HomeFinderTheme {
        DetailsScreen(
            selectedHouse = House(
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
            onBackPressed = { true }
        )
    }
}