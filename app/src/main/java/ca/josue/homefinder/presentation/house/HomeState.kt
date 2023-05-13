package ca.josue.homefinder.presentation.house

import androidx.annotation.StringRes
import androidx.paging.PagingData
import ca.josue.homefinder.domain.models.House
import kotlinx.coroutines.flow.Flow

sealed class HomeState {
    data object Idle : HomeState()
    data object Loading : HomeState()
    data class Success(val houses: Flow<PagingData<House>>) : HomeState()
    data class Error(@StringRes val message: Int) : HomeState()
}
