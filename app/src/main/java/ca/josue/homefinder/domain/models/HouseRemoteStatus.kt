package ca.josue.homefinder.domain.models

import androidx.paging.PagingData
import ca.josue.homefinder.utils.HttpError
import kotlinx.coroutines.flow.Flow
sealed class HouseRemoteStatus : Status {
    data class Success(val data: Flow<PagingData<House>>) : HouseRemoteStatus()
    data class Error(val exception: HttpError) : HouseRemoteStatus()
}
