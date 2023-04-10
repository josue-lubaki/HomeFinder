package ca.josue.homefinder.presentation.house

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.HouseStatus
import ca.josue.homefinder.domain.usecases.UseCases
import ca.josue.homefinder.domain.usecases.read_access_token.ReadAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
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
    private val readeAccessTokenUseCase: ReadAccessTokenUseCase
): ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Idle)
    val state : StateFlow<HomeState> = _state.asStateFlow()

    private sealed class HomeAction {
        object GetAllHouses : HomeAction()
        data class GetAllHousesSuccess(val houses: Flow<PagingData<House>>) : HomeAction()
        data class GetAllHousesError(val exception: Exception) : HomeAction()
    }

    private fun dispatch(action : HomeAction){
        when(action){
            is HomeAction.GetAllHouses -> {
                _state.value = HomeState.Loading
                viewModelScope.launch(dispatchers) {
                    val token = readeAccessTokenUseCase().stateIn(this).value
                    when(val result = useCase.getAllHousesUseCase(token)){
                        is HouseStatus.Success -> {
                            dispatch(HomeAction.GetAllHousesSuccess(result.houses))
                        }
                        is HouseStatus.Error -> {
                            dispatch(HomeAction.GetAllHousesError(result.exception))
                        }
                    }
                }
            }
            is HomeAction.GetAllHousesSuccess -> {
                _state.value = HomeState.Success(action.houses)
            }
            is HomeAction.GetAllHousesError -> {
                _state.value = HomeState.Error(action.exception)
            }
        }
    }

    fun onGetAllHouses(){
        dispatch(HomeAction.GetAllHouses)
    }
}