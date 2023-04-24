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
import androidx.compose.ui.unit.dp
import ca.josue.homefinder.ui.theme.HomeFinderTheme
import ca.josue.homefinder.ui.theme.dimensions

/**
 * created by Josue Lubaki
 * date : 2023-04-24
 * version : 1.0.0
 */

@Composable
fun HouseItem(
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
                minWidth = MaterialTheme.dimensions.xxLarge,
                minHeight = MaterialTheme.dimensions.semiExtraLarge,
                maxWidth = MaterialTheme.dimensions.xxxLarge,
                maxHeight = MaterialTheme.dimensions.semiExtraLarge
            )
            .padding(horizontal = MaterialTheme.dimensions.semiMedium),
//        contentAlignment = Alignment.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(
                modifier = Modifier
                    .padding(end = MaterialTheme.dimensions.small)
                    .size(24.dp)
                ,
                imageVector = trailingIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )

            content()
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun HouseItemPreview() {
    HomeFinderTheme {
        HouseItem(
            trailingIcon = Icons.Outlined.SquareFoot,
            content = {
                Text(
                    text = "1",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}