package ca.josue.homefinder.presentation.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ca.josue.homefinder.R
import ca.josue.homefinder.ui.theme.dimensions
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.pager.HorizontalPagerIndicator

/**
 * created by Josue Lubaki
 * date : 2023-04-23
 * version : 1.0.0
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BackgroundContent(
    houseImagesUrl: List<String>,
    onCloseClicked: () -> Boolean,
    onViewImageClicked: (String) -> Unit,
) {
    val pagerState = rememberPagerState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        if (houseImagesUrl.first().isEmpty()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = "Placeholder Picture",
                contentScale = ContentScale.Crop,
            )
        }
        else {
            HorizontalPager(
                pageCount = houseImagesUrl.size - 1,
                state = pagerState
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onViewImageClicked(houseImagesUrl[it]) }
                    ,
                    painter = rememberAsyncImagePainter(
                        placeholder = painterResource(id = R.drawable.ic_placeholder),
                        error = painterResource(id = R.drawable.ic_placeholder),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(houseImagesUrl[it])
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
                pageCount = houseImagesUrl.size - 1,
                activeColor = Color.White,
                inactiveColor = Color.White.copy(alpha = 0.2f),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = MaterialTheme.dimensions.semiMedium),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(MaterialTheme.dimensions.large),
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.Black,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BackgroundContentPreview() {
    BackgroundContent(
        houseImagesUrl = listOf(),
        onCloseClicked = { false },
        onViewImageClicked = { }
    )
}