package ca.josue.homefinder.presentation.components


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

/**
 * created by Josue Lubaki
 * date : 2023-04-24
 * version : 1.0.0
 */

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun TitlePreview() {
    Title(
        text = "Type de la propriété",
    )
}