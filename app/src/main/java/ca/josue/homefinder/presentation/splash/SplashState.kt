package ca.josue.homefinder.presentation.splash

sealed class SplashState {
    object Completed : SplashState()
    object OnBoardingNotCompleted : SplashState()
}
