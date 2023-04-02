package ca.josue.homefinder.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.josue.homefinder.data.local.dao.address.AddressDao
import ca.josue.homefinder.data.local.dao.house.HouseDao
import ca.josue.homefinder.data.local.dao.house.HouseRemoteKeysDao
import ca.josue.homefinder.data.local.dao.owner.OwnerDao
import ca.josue.homefinder.domain.models.Address
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.models.HouseRemoteKeys
import ca.josue.homefinder.domain.models.Owner

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

@Database(
    entities = [
        House::class,
        Owner::class,
        Address::class,
        HouseRemoteKeys::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class HomeFinderDB : RoomDatabase() {

        abstract fun houseDao(): HouseDao
        abstract fun ownerDao(): OwnerDao
        abstract fun addressDao(): AddressDao
        abstract fun houseRemoteKeysDao(): HouseRemoteKeysDao

    companion object {
            fun create(context : Context, useInMemory: Boolean) : HomeFinderDB {
                val databaseBuilder = if(useInMemory) {
                    Room.inMemoryDatabaseBuilder(context, HomeFinderDB::class.java)
                } else {
                    Room.databaseBuilder(context, HomeFinderDB::class.java, "homefinder.db")
                }

                return databaseBuilder
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }


}