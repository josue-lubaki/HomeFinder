package ca.josue.homefinder.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.HouseLocalStatus
import ca.josue.homefinder.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-04-23
 * version : 1.0.0
 */

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel(){
    private val _state = MutableStateFlow<DetailState>(DetailState.Idle)
    val state : StateFlow<DetailState> = _state

    private sealed class DetailsAction {
        data class GetHouseDetails(val houseId: Int) : DetailsAction()
        data class GetHouseDetailsSuccess(val house: House) : DetailsAction()
        data class GetHouseDetailsError(val exception: Exception) : DetailsAction()
    }

    private fun dispatch(action: DetailsAction) {
        when (action) {
            is DetailsAction.GetHouseDetails -> action.reduce()
            is DetailsAction.GetHouseDetailsSuccess -> action.reduce()
            is DetailsAction.GetHouseDetailsError -> action.reduce()
        }
    }

    private fun DetailsAction.GetHouseDetails.reduce() {
        _state.value = DetailState.Idle
        viewModelScope.launch {
            when (val result = useCases.getHouseDetailsUseCase(houseId)) {
                is HouseLocalStatus.Error -> dispatch(DetailsAction.GetHouseDetailsError(result.exception))
                is HouseLocalStatus.Success -> dispatch(DetailsAction.GetHouseDetailsSuccess(result.house))
            }
        }
    }

    private fun DetailsAction.GetHouseDetailsSuccess.reduce() {
        _state.value = DetailState.Success(house)
    }

    private fun DetailsAction.GetHouseDetailsError.reduce() {
        _state.value = DetailState.Error(exception.message)
    }

    fun getHouseDetails(houseId: Int) {
        dispatch(DetailsAction.GetHouseDetails(houseId))
    }

}