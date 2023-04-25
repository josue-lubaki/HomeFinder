package ca.josue.homefinder.presentation.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.josue.homefinder.R
import ca.josue.homefinder.data.models.house.HouseType
import ca.josue.homefinder.domain.models.Address
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.Owner
import ca.josue.homefinder.ui.theme.HomeFinderTheme
import ca.josue.homefinder.ui.theme.dimensions
import ca.josue.homefinder.utils.Constants.getHomeTypeName
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.pager.HorizontalPagerIndicator

/**
 * created by Josue Lubaki
 * date : 2023-04-05
 * version : 1.0.0
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardHouse(
    house: House,
    isLiked: Boolean = false,
    windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    onHouseClicked: (Int) -> Unit,
) {

    val images = house.images
    val isLarge = windowSize == WindowWidthSizeClass.Expanded
    val imageSize =
        if (isLarge) MaterialTheme.dimensions.imageHeightSmall
        else MaterialTheme.dimensions.imageHeightDefault

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimensions.small)
            .clip(
                RoundedCornerShape(
                    topStart = MaterialTheme.dimensions.semiMedium,
                    topEnd = MaterialTheme.dimensions.semiMedium,
                )
            )
            .clickable { onHouseClicked(house.uuid) },
        verticalArrangement = Arrangement.Top
    ) {


        val shimmerColor = listOf(
            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f),
            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
        )
        val infiniteTransition = rememberInfiniteTransition(label = house.id)
        val position by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 4600f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 750,
                    easing = FastOutLinearInEasing
                ),
                repeatMode = RepeatMode.Reverse
            ),
            label = house.id
        )
        val gradient = Brush.linearGradient(
            shimmerColor,
            start = Offset(0f, 0f),
            end = Offset(position, 0f)
        )

        val painter = rememberAsyncImagePainter(
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder),
            model = ImageRequest.Builder(LocalContext.current)
                .data(images[0])
                .crossfade(true)
                .size(Size.ORIGINAL)
                .build()
        )

        val pagerState = rememberPagerState()

        when (painter.state) {
            is AsyncImagePainter.State.Success -> {
                Box {
                    HorizontalPager(
                        pageCount = images.size - 1,
                        modifier = Modifier.height(imageSize),
                        state = pagerState
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(images[it])
                                    .crossfade(true)
                                    .size(Size.ORIGINAL)
                                    .build()
                            ),
                            contentDescription = "House Picture",
                            contentScale = ContentScale.Crop,
                        )
                    }


                    HorizontalPagerIndicator(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.dimensions.small)
                            .align(Alignment.BottomCenter),
                        pagerState = pagerState,
                        pageCount = images.size - 1,
                        activeColor = Color.White,
                        inactiveColor = Color.White.copy(alpha = 0.2f),
                    )
                }
            }

            is AsyncImagePainter.State.Loading -> {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(gradient)
                        .height(imageSize),
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(
                        topStart = MaterialTheme.dimensions.semiMedium,
                        topEnd = MaterialTheme.dimensions.semiMedium,
                    )
                ) {}
            }

            else -> {
                Image(
                    modifier = Modifier.height(imageSize),
                    painter = painterResource(id = R.drawable.ic_placeholder),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                .padding(MaterialTheme.dimensions.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.tiny),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                FormattedPrice(price = house.price.toDouble())

                IconButton(
                    modifier = Modifier.height(MaterialTheme.dimensions.semiExtraLarge),
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite Icon",
                        tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.semiSmall),
            ) {
                Icon(
                    modifier = Modifier.height(MaterialTheme.typography.bodyLarge.fontSize.value.dp),
                    imageVector = Icons.Outlined.Place,
                    contentDescription = "Place Icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                )

                Text(
                    text = house.address.city,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                )
            }

            Text(
                modifier = Modifier.padding(start = MaterialTheme.dimensions.semiSmall),
                text = "${house.address.number}, ${house.address.street}",
                style = MaterialTheme.typography.bodyMedium,
            )

            Tag(text = stringResource(id = getHomeTypeName(house.type)))

            Text(
                text = house.description,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}



@Preview
@Composable
private fun CardHousePreview() {
    HomeFinderTheme {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                )
                .padding(MaterialTheme.dimensions.small),
        ) {
            CardHouse(
                windowSize = WindowWidthSizeClass.Expanded,
                isLiked = true,
                onHouseClicked = { /*TODO*/ },
                house = House(
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
                )
            )
        }
    }
}