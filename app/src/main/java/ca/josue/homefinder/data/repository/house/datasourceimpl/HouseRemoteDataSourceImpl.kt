package ca.josue.homefinder.data.repository.house.datasourceimpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ca.josue.homefinder.data.local.db.HomeFinderDB
import ca.josue.homefinder.data.paging_source.HouseRemoteMediator
import ca.josue.homefinder.data.remote.HouseService
import ca.josue.homefinder.data.repository.house.datasource.HouseRemoteDataSource
import ca.josue.homefinder.domain.models.HouseRemoteStatus
import ca.josue.homefinder.utils.Constants.ITEMS_PER_PAGE

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

@OptIn(ExperimentalPagingApi::class)
class HouseRemoteDataSourceImpl(
    private val service: HouseService,
    private val database: HomeFinderDB,
) : HouseRemoteDataSource {

    private val houseDao = database.houseDao()
    override fun getAllHouses(): HouseRemoteStatus {
        val pagingSourceFactory = { houseDao.getAllHouses() }
        return try {
            HouseRemoteStatus.Success(
                Pager(
                    config = PagingConfig(
                        pageSize = ITEMS_PER_PAGE,
                    ),
                    remoteMediator = HouseRemoteMediator(
                        service = service,
                        database = database
                    ),
                    pagingSourceFactory = pagingSourceFactory
                ).flow
            )
        } catch (e: Exception) {
            HouseRemoteStatus.Error(e)
        }
    }
}