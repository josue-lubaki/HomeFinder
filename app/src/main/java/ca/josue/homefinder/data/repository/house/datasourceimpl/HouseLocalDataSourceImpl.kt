package ca.josue.homefinder.data.repository.house.datasourceimpl

import androidx.paging.PagingSource
import ca.josue.homefinder.data.local.dao.house.HouseDao
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.data.repository.house.datasource.HouseLocalDataSource

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

class HouseLocalDataSourceImpl(
    private val houseDao: HouseDao
) : HouseLocalDataSource {
    override suspend fun getHousesFromDB(): PagingSource<Int, House> {
        return houseDao.getAllHouses()
    }

    override suspend fun getHouseFromDB(id: String): House {
        return houseDao.getHouseById(id)
    }

    override suspend fun saveHousesToDB(houses: List<House>) {
        houseDao.insertHouses(houses)
    }

    override suspend fun clearAll() {
        houseDao.deleteAllHouses()
    }
}