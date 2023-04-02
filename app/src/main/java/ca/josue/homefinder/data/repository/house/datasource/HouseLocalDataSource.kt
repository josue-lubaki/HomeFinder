package ca.josue.homefinder.data.repository.house.datasource

import androidx.paging.PagingSource
import ca.josue.homefinder.domain.models.House

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

interface HouseLocalDataSource {
    suspend fun getHousesFromDB(): PagingSource<Int, House>

    suspend fun getHouseFromDB(id: String): House
    suspend fun saveHousesToDB(houses: List<House>)
    suspend fun clearAll()
}