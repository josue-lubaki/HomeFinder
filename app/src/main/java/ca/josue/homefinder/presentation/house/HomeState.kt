package ca.josue.homefinder.presentation.house

import androidx.paging.PagingData
import ca.josue.homefinder.domain.models.House
import kotlinx.coroutines.flow.Flow

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    data class Success(val houses: Flow<PagingData<House>>) : HomeState()
    data class Error(val exception: Exception) : HomeState()
}
