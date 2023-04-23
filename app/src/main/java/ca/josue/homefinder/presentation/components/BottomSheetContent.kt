package ca.josue.homefinder.presentation.components


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.josue.homefinder.data.models.house.HouseType
import ca.josue.homefinder.domain.models.Address
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.Owner
import ca.josue.homefinder.ui.theme.dimensions
import ca.josue.homefinder.utils.Constants
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * created by Josue Lubaki
 * date : 2023-04-23
 * version : 1.0.0
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(
    scaffoldState: BottomSheetScaffoldState,
    selectedHouse : House,
    onToggleBottomSheetState : () -> Job,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = Modifier.padding(
                top = MaterialTheme.dimensions.medium,
                bottom = MaterialTheme.dimensions.medium
            ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ){
            IconButton(onClick = {
                onToggleBottomSheetState()
            }) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector =
                        if(scaffoldState.bottomSheetState.isCollapsed) Icons.Filled.KeyboardArrowUp
                        else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimensions.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.small),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                FormattedPrice(
                    price = selectedHouse.price.toDouble(),
                    fontWeight = FontWeight.ExtraBold,
                )

                IconButton(
                    modifier = Modifier.height(MaterialTheme.dimensions.semiExtraLarge),
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = if (selectedHouse.isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite Icon",
                        tint = if (selectedHouse.isLiked) Color.Red else MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
            Tag(text = stringResource(id = Constants.getHomeTypeName(selectedHouse.type)))
            Text(
                text = selectedHouse.description,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
            )
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
private fun BottomSheetContentPreview() {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessHigh
            )
        )
    )

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .padding(16.dp),
    ){
        BottomSheetContent(
            selectedHouse = House(
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
            scaffoldState = scaffoldState,
            onToggleBottomSheetState = {
                scope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            },
        )
    }
}