package ca.josue.homefinder.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.josue.homefinder.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    useCases : UseCases,
    dispatchers : CoroutineDispatcher
) : ViewModel() {

    private val _onBoardingState = MutableStateFlow<SplashState>(SplashState.OnBoardingNotCompleted)
    val onBoardingState : StateFlow<SplashState> = _onBoardingState

    init {
        viewModelScope.launch(dispatchers){
            when(useCases.readOnBoardingUseCase().stateIn(this).value){
                true -> _onBoardingState.value = SplashState.Completed
                false -> _onBoardingState.value = SplashState.OnBoardingNotCompleted
            }
        }
    }
}