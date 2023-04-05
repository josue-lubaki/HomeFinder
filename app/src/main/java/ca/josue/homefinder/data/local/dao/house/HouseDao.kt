package ca.josue.homefinder.data.local.dao.house

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.josue.homefinder.domain.models.House

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

@Dao
interface HouseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHouse(house: House)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHouses(houses: List<House>)

    @Query("DELETE FROM houses_table")
    suspend fun deleteAllHouses()

    @Query("SELECT * FROM houses_table ORDER BY uuid DESC")
    fun getAllHouses(): PagingSource<Int, House>

    @Query("SELECT * FROM houses_table WHERE id = :id")
    suspend fun getHouseById(id: String): House

}