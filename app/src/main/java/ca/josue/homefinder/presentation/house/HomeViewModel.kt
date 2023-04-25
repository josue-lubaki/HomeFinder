package ca.josue.homefinder.presentation.house

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.HouseRemoteStatus
import ca.josue.homefinder.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UseCases,
    private val dispatchers: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Idle)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private sealed class HomeAction {
        object GetAllHouses : HomeAction()
        data class UpdateHouseLike(val uuid: Int, val liked: Boolean) : HomeAction()
        data class GetAllHousesSuccess(val houses: Flow<PagingData<House>>) : HomeAction()
        data class GetAllHousesError(val exception: Exception) : HomeAction()
    }

    private fun dispatch(action: HomeAction) {
        when (action) {
            is HomeAction.GetAllHouses -> {
                _state.value = HomeState.Loading
                viewModelScope.launch(dispatchers) {
                    when (val result = useCase.getAllHousesUseCase()) {
                        is HouseRemoteStatus.Success -> {
                            dispatch(HomeAction.GetAllHousesSuccess(result.data))
                        }

                        is HouseRemoteStatus.Error -> {
                            dispatch(HomeAction.GetAllHousesError(result.exception))
                        }
                    }
                }
            }

            is HomeAction.GetAllHousesSuccess -> {
                viewModelScope.launch(dispatchers) {
                    _state.value = HomeState.Success(action.houses)
                }
            }

            is HomeAction.GetAllHousesError -> {
                viewModelScope.launch(dispatchers) {
                    _state.value = HomeState.Error(action.exception)
                }
            }

            is HomeAction.UpdateHouseLike -> {
                viewModelScope.launch(Dispatchers.Main) {
                    useCase.updateHouseLikeUseCase(action.uuid, action.liked)
                }
            }
        }
    }

    fun onGetAllHouses() {
        dispatch(HomeAction.GetAllHouses)
    }

    fun onUpdateHouseLike(uuid: Int, liked: Boolean) {
        dispatch(HomeAction.UpdateHouseLike(uuid, liked.not()))
    }
}