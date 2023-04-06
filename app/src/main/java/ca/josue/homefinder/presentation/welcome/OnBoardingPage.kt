package ca.josue.homefinder.presentation.welcome

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ca.josue.homefinder.R

sealed class OnBoardingPage(
    @DrawableRes val image : Int,
    @StringRes val title : Int,
    @StringRes val description : Int
){
    object First : OnBoardingPage(
        image = R.drawable.welcome_first,
        title = R.string.welcome,
        description = R.string.welcome_slide_desciption
    )
    object Second : OnBoardingPage(
        image = R.drawable.welcome_first,
        title = R.string.find_your_home,
        description = R.string.find_your_home_description
    )

    object Third : OnBoardingPage(
        image = R.drawable.welcome_first,
        title = R.string.commitment_to_you,
        description = R.string.commitment_to_you_desciption
    )
}
