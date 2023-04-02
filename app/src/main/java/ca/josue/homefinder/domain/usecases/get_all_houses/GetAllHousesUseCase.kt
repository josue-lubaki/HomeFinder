package ca.josue.homefinder.domain.usecases.get_all_houses

import androidx.paging.PagingData
import ca.josue.homefinder.data.repository.Repository
import ca.josue.homefinder.domain.models.House
import kotlinx.coroutines.flow.Flow

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

class GetAllHousesUseCase(
    private val repository: Repository
) {
    operator fun invoke() : Flow<PagingData<House>> = repository.getAllHouses()
}