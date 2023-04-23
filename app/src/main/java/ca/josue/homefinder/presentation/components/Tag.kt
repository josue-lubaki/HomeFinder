package ca.josue.homefinder.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ca.josue.homefinder.ui.theme.dimensions

/**
 * created by Josue Lubaki
 * date : 2023-04-23
 * version : 1.0.0
 */

@Composable
fun Tag(text : String) {
    Box(
        modifier = Modifier
            .padding(vertical = MaterialTheme.dimensions.semiSmall)
            .background(
                color = Color.Green.copy(alpha = 0.4f),
                shape = RoundedCornerShape(MaterialTheme.dimensions.small)
            )
            .padding(
                horizontal = MaterialTheme.dimensions.small,
                vertical = MaterialTheme.dimensions.semiSmall
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TagPreview() {
    Tag(text = "Hello World")
}