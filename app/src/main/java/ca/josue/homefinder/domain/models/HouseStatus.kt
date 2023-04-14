package ca.josue.homefinder.domain.models

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

sealed class HouseStatus {
    data class Success(val houses: Flow<PagingData<House>>) : HouseStatus()
    data class Error(val exception: Exception) : HouseStatus()
}
