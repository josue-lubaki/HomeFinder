package ca.josue.homefinder.data.repository

import androidx.paging.PagingData
import ca.josue.homefinder.data.repository.house.datasource.HouseLocalDataSource
import ca.josue.homefinder.data.repository.house.datasource.HouseRemoteDataSource
import ca.josue.homefinder.domain.models.House
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

class Repository @Inject constructor(
    private val localDataSource: HouseLocalDataSource,
    private val remoteDataSource: HouseRemoteDataSource
) {
    fun getAllHouses(): Flow<PagingData<House>> {
        return remoteDataSource.getAllHouses()
    }

    suspend fun getSelectedHouse(id: String): House {
        return localDataSource.getHouseFromDB(id)
    }

    // TODO : Add other methods (onSaveBoardingState and onReadBoardingState)


}