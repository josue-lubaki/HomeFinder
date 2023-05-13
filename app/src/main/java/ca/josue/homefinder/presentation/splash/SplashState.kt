package ca.josue.homefinder.presentation.splash

sealed class SplashState {
    data object Completed : SplashState()
    data object OnBoardingNotCompleted : SplashState()
}
