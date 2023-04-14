package ca.josue.homefinder.data.repository.house.datasource

import ca.josue.homefinder.domain.models.HouseStatus

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

interface HouseRemoteDataSource {
    fun getAllHouses(): HouseStatus
}