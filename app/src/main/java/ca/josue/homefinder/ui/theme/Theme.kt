package ca.josue.homefinder.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark10,
    onPrimary = primaryDark100,
    primaryContainer = primaryDark80,
    onPrimaryContainer = primaryDark10,
    secondary = secondaryDark50,
    onSecondary = secondaryDark100,
    secondaryContainer = secondaryDark80,
    onSecondaryContainer = secondaryDark10,
    tertiary = tertiaryDark50,
    onTertiary = tertiaryDark100,
    tertiaryContainer = tertiaryDark80,
    onTertiaryContainer = tertiaryDark10,
    error = errorDark50,
    onError = errorDark100,
    errorContainer = errorDark80,
    onErrorContainer = errorDark10,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
)

private val LightColorScheme = lightColorScheme(
    primary = primary10,
    onPrimary = primary100,
    primaryContainer = primary80,
    onPrimaryContainer = primary10,
    secondary = secondary50,
    onSecondary = secondary100,
    secondaryContainer = secondary80,
    onSecondaryContainer = secondary10,
    tertiary = tertiary50,
    onTertiary = tertiary100,
    tertiaryContainer = tertiary80,
    onTertiaryContainer = tertiary10,
    error = error50,
    onError = error100,
    errorContainer = error80,
    onErrorContainer = error10,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    outline = outline,
)

@Composable
fun HomeFinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}