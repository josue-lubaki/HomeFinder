package ca.josue.homefinder.presentation.components


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import java.text.NumberFormat

/**
 * created by Josue Lubaki
 * date : 2023-04-23
 * version : 1.0.0
 */

@Composable
fun FormattedPrice(
    price: Double,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    fontWeight: FontWeight = FontWeight.SemiBold
) {
    val formatter = NumberFormat.getInstance().apply {
        minimumFractionDigits = 0
        maximumFractionDigits = 0
    }
    val finalFormattedPrice = formatter.format(price)

    Text(
        text = "$finalFormattedPrice $",
        style = style,
        fontWeight = fontWeight
    )
}
@Preview(showBackground = true)
@Composable
private fun FormattedPricePreview() {
    FormattedPrice(
        price = 150230.toDouble(),
    )
}