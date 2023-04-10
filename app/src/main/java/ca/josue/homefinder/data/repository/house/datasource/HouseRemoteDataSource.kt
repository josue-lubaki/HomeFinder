package ca.josue.homefinder.data.repository.house.datasource

import androidx.paging.PagingData
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.HouseStatus
import kotlinx.coroutines.flow.Flow

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

interface HouseRemoteDataSource {
    fun getAllHouses(token : String): HouseStatus
}