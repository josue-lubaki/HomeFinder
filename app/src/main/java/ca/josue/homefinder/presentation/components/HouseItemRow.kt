package ca.josue.homefinder.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.House
import androidx.compose.material.icons.outlined.SquareFoot
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ca.josue.homefinder.ui.theme.HomeFinderTheme
import ca.josue.homefinder.ui.theme.dimensions

/**
 * created by Josue Lubaki
 * date : 2023-04-24
 * version : 1.0.0
 */

@Composable
fun HouseItemRow(
    trailingIcon : ImageVector,
    content : @Composable () -> Unit,
) {

    Column(
        modifier = Modifier
            .background(
                color = Color.Green.copy(alpha = 0.4f),
                shape = RoundedCornerShape(MaterialTheme.dimensions.small)
            )
            .requiredSizeIn(
                minWidth = MaterialTheme.dimensions.extraXLarge,
                minHeight = MaterialTheme.dimensions.semiExtraLarge,
                maxWidth = MaterialTheme.dimensions.xxxLarge,
                maxHeight = MaterialTheme.dimensions.semiExtraLarge
            )
            .padding(horizontal = MaterialTheme.dimensions.semiMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ){
            HouseItemContent(
                modifier = Modifier
                    .padding(end = MaterialTheme.dimensions.small),
                icon = trailingIcon,
                content = content,
            )
        }
    }
}

@Composable
fun HouseItemColumn(
    icon : ImageVector,
    iconSize : Dp = MaterialTheme.dimensions.semiExtraLarge,
    content : @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(
                color = Color.Green.copy(alpha = 0.4f),
                shape = RoundedCornerShape(MaterialTheme.dimensions.small)
            )
            .requiredSizeIn(
                minWidth = MaterialTheme.dimensions.xxLarge,
                minHeight = MaterialTheme.dimensions.semiXXLarge,
                maxWidth = MaterialTheme.dimensions.extraXXXLarge,
                maxHeight = MaterialTheme.dimensions.xxxLarge
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HouseItemContent(
            icon = icon,
            iconSize = iconSize,
            content = content
        )
    }
}

@Composable
fun HouseItemContent(
    modifier: Modifier = Modifier,
    icon : ImageVector,
    iconSize : Dp = 24.dp,
    content : @Composable () -> Unit,
) {
    Icon(
        modifier = modifier
            .size(iconSize),
        imageVector = icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSurface,
    )

    content()
}

@Preview(showBackground = true)
@Composable
private fun HouseItemPreview() {
    HomeFinderTheme {
        HouseItemRow(
            trailingIcon = Icons.Outlined.SquareFoot,
            content = {
                Text(
                    text = "965 pi²",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}

@Preview
@Composable
private fun HouseItemColumn() {
    HomeFinderTheme {
        HouseItemColumn(
            icon = Icons.Outlined.House,
            iconSize = MaterialTheme.dimensions.semiExtraLarge,
            content = {
                Text(
                    text = "Single Family",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}