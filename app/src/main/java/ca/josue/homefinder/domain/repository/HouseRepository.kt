package ca.josue.homefinder.domain.repository

import androidx.paging.PagingData
import ca.josue.homefinder.domain.models.House
import kotlinx.coroutines.flow.Flow

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

interface HouseRepository {
    fun getAllHouses() : Flow<PagingData<House>>
    suspend fun getSelectedHouse(id: String) : House
}