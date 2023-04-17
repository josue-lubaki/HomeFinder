package ca.josue.homefinder.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

data class Dimensions(
    val default : Dp = 0.dp,
    val tiny : Dp = 2.dp,
    val semiSmall : Dp = 4.dp,
    val small : Dp = 8.dp,
    val semiMedium : Dp = 12.dp,
    val medium : Dp = 16.dp,
    val semiLarge : Dp = 24.dp,
    val large : Dp = 32.dp,
    val semiExtraLarge : Dp = 36.dp,
    val extraLarge : Dp = 48.dp,
    val semiXLarge : Dp = 56.dp,
    val xLarge: Dp = 64.dp,
    val semiXXLarge : Dp = 80.dp,
    val xxLarge : Dp = 96.dp,

    val paddingDefault : Dp = 0.dp,
    val paddingTiny : Dp = 4.dp,
    val paddingSemiSmall : Dp = 8.dp,
    val paddingSmall : Dp = 12.dp,
    val paddingSemiMedium : Dp = 16.dp,
    val paddingMedium : Dp = 20.dp,
    val paddingSemiLarge : Dp = 24.dp,
    val paddingLarge : Dp = 32.dp,
    val paddingExtraLarge : Dp = 48.dp,
    val paddingSemiXLarge : Dp = 64.dp,
    val paddingXLarge : Dp = 80.dp,

    val fontSizeDefault : TextUnit = 12.sp,
    val fontSizeTiny : TextUnit = 14.sp,
    val fontSizeSemiSmall : TextUnit = 16.sp,
    val fontSizeSmall : TextUnit = 18.sp,
    val fontSizeSemiMedium : TextUnit = 20.sp,
    val fontSizeMedium : TextUnit = 22.sp,
    val fontSizeSemiLarge : TextUnit = 24.sp,
    val fontSizeLarge : TextUnit = 26.sp,
    val fontSizeExtraLarge : TextUnit = 28.sp,
    val fontSizeSemiXLarge : TextUnit = 30.sp,
    val fontSizeXLarge : TextUnit = 32.sp,
    val fontSizeSemiXXLarge : TextUnit = 34.sp,
    val fontSizeXXLarge : TextUnit = 36.sp,

    val letterSpacingDefault : TextUnit = 0.sp,
    val letterSpacingTiny : TextUnit = 0.5.sp,
    val letterSpacingSemiSmall : TextUnit = 1.sp,
    val letterSpacingSmall : TextUnit = 1.5.sp,
    val letterSpacingSemiMedium : TextUnit = 2.sp,
    val letterSpacingMedium : TextUnit = 2.5.sp,
    val letterSpacingSemiLarge : TextUnit = 3.sp,
    val letterSpacingLarge : TextUnit = 3.5.sp,
    val letterSpacingExtraLarge : TextUnit = 4.sp,

    val elevationDefault : Dp = 0.dp,
    val elevationTiny : Dp = 1.dp,
    val elevationSemiSmall : Dp = 2.dp,
    val elevationSmall : Dp = 3.dp,
    val elevationSemiMedium : Dp = 4.dp,
    val elevationMedium : Dp = 5.dp,
    val elevationSemiLarge : Dp = 6.dp,
    val elevationLarge : Dp = 7.dp,
    val elevationExtraLarge : Dp = 8.dp,

    val radiusDefault : Dp = 0.dp,
    val radiusTiny : Dp = 2.dp,
    val radiusSemiSmall : Dp = 4.dp,
    val radiusSmall : Dp = 6.dp,
    val radiusSemiMedium : Dp = 8.dp,
    val radiusMedium : Dp = 10.dp,
    val radiusSemiLarge : Dp = 12.dp,
    val radiusLarge : Dp = 14.dp,
    val radiusExtraLarge : Dp = 16.dp,

    val borderWithDefault : Dp = 0.dp,
    val borderWithTiny : Dp = 1.dp,
    val borderWithSemiSmall : Dp = 2.dp,
    val borderWithSmall : Dp = 3.dp,
    val borderWithSemiMedium : Dp = 4.dp,
    val borderWithMedium : Dp = 5.dp,
    val borderWithSemiLarge : Dp = 6.dp,
    val borderWithLarge : Dp = 7.dp,
    val borderWithExtraLarge : Dp = 8.dp,

    val imageHeightDefault : Dp = 440.dp,
    val imageHeightSmall : Dp = 280.dp
)

val LocalDimension = compositionLocalOf { Dimensions() }
val MaterialTheme.dimensions: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimension.current

