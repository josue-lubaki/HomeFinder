package ca.josue.homefinder.presentation.login

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ca.josue.homefinder.ui.theme.HomeFinderTheme

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

@Composable
fun LoginScreen(
    windowSize : WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    viewModel : LoginViewModel
) {

    // TODO: Implement LoginScreen
    Text(
        text = "Login Screen",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview
@Composable
private fun LoginScreenPreview() {
    HomeFinderTheme {
        LoginScreen(
            viewModel = LoginViewModel()
        )
    }
}