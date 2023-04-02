package ca.josue.homefinder.data.local.dao.house

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.josue.homefinder.domain.models.HouseRemoteKeys

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

@Dao
interface HouseRemoteKeysDao {

    @Query("SELECT * FROM house_remote_keys WHERE uuid = :uuid")
    suspend fun getRemoteKeysById(uuid : Int) : HouseRemoteKeys?

    // get last updated house remote keys
    @Query("SELECT * FROM house_remote_keys ORDER BY lastUpdated DESC LIMIT 1")
    suspend fun getLastUpdatedRemoteKeys() : HouseRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(remoteKeys : List<HouseRemoteKeys>)

    @Query("DELETE FROM house_remote_keys")
    suspend fun deleteAllRemoteKeys()
}