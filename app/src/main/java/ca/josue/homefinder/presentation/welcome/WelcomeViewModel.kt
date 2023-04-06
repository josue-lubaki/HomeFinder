package ca.josue.homefinder.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.josue.homefinder.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCases : UseCases,
    private val dispatchers : CoroutineDispatcher
) : ViewModel() {

    fun saveOnBoardingState(completed : Boolean) {
        viewModelScope.launch(dispatchers) {
            useCases.saveOnBoardingUseCase(completed)
        }
    }
}