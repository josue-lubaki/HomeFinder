package ca.josue.homefinder.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ca.josue.homefinder.R
import ca.josue.homefinder.ui.theme.dimensions
import coil.compose.rememberAsyncImagePainter

/**
 * created by Josue Lubaki
 * date : 2023-04-23
 * version : 1.0.0
 */

@Composable
fun BackgroundContent(
    houseImageUrl : String,
    imageFraction : Float = 1f,
    onCloseClicked : () -> Boolean,
) {
    val painter = rememberAsyncImagePainter(
        model = houseImageUrl,
        placeholder = painterResource(id = R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(
                modifier = Modifier.padding(all = MaterialTheme.dimensions.semiMedium),
                onClick = { onCloseClicked() }
            ){
                Icon(
                    modifier = Modifier.size(MaterialTheme.dimensions.large),
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BackgroundContentPreview() {
    BackgroundContent(
        houseImageUrl = "",
        imageFraction = 1f,
        onCloseClicked = { false },
    )
}