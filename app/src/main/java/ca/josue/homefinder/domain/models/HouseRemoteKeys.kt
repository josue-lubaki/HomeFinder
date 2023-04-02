package ca.josue.homefinder.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

@Entity(tableName = "house_remote_keys")
data class HouseRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val uuid: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated : Long?
)