package ca.josue.homefinder.data.repository

import androidx.paging.PagingData
import ca.josue.homefinder.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import ca.josue.homefinder.data.repository.house.datasource.HouseLocalDataSource
import ca.josue.homefinder.data.repository.house.datasource.HouseRemoteDataSource
import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.models.AuthenticationStatus
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.HouseStatus
import ca.josue.homefinder.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

class Repository @Inject constructor(
    private val localDataSource: HouseLocalDataSource,
    private val remoteDataSource: HouseRemoteDataSource,
    private val dataStore : DataStoreOperations,
) {
    fun getAllHouses(token : String): HouseStatus {
        return remoteDataSource.getAllHouses(token)
    }

    suspend fun getSelectedHouse(id: String): House {
        return localDataSource.getHouseFromDB(id)
    }

    suspend fun onSaveBoardingState(completed : Boolean){
        dataStore.saveOnBoardingState(completed)
    }

    fun onReadOnBoardingState() : Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

//    fun onReadTokenAccess() : Flow<String> {
//        return dataStore.readTokenAccess()
//    }

    suspend fun onSaveTokenAccess(token: String){
        dataStore.saveTokenAccess(token)
    }

}