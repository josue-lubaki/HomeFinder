package ca.josue.homefinder.di

import android.content.Context
import androidx.room.Room
import ca.josue.homefinder.data.local.dao.house.HouseDao
import ca.josue.homefinder.data.local.db.HomeFinderDB
import ca.josue.homefinder.data.repository.house.datasource.HouseLocalDataSource
import ca.josue.homefinder.data.repository.house.datasourceimpl.HouseLocalDataSourceImpl
import ca.josue.homefinder.utils.Constants.HOME_FINDER_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context): HomeFinderDB {
        return Room.databaseBuilder(context, HomeFinderDB::class.java, HOME_FINDER_DB).build()
    }

    @Singleton
    @Provides
    fun provideHouseDao(database: HomeFinderDB): HouseDao {
        return database.houseDao()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(houseDao: HouseDao) : HouseLocalDataSource {
        return HouseLocalDataSourceImpl(houseDao)
    }
}