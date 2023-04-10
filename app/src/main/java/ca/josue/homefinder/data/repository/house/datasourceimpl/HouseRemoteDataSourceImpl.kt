package ca.josue.homefinder.data.repository.house.datasourceimpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ca.josue.homefinder.data.local.db.HomeFinderDB
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.data.paging_source.HouseRemoteMediator
import ca.josue.homefinder.data.remote.HouseService
import ca.josue.homefinder.data.repository.house.datasource.HouseRemoteDataSource
import ca.josue.homefinder.domain.models.HouseStatus
import ca.josue.homefinder.domain.usecases.read_access_token.ReadAccessTokenUseCase
import ca.josue.homefinder.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

@OptIn(ExperimentalPagingApi::class)
class HouseRemoteDataSourceImpl(
    private val service: HouseService,
    private val database : HomeFinderDB,
    private val readAccessTokenUseCase : ReadAccessTokenUseCase
) : HouseRemoteDataSource {

    private val houseDao = database.houseDao()
    override fun getAllHouses(token : String): HouseStatus {
        val pagingSourceFactory = { houseDao.getAllHouses() }
        return try {
            HouseStatus.Success(
                Pager(
                    config = PagingConfig(
                        pageSize = ITEMS_PER_PAGE,
                    ),
                    remoteMediator = HouseRemoteMediator(
                        service = service,
                        database = database,
                        token = token
                    ),
                    pagingSourceFactory = pagingSourceFactory
                ).flow
            )
        } catch (e: Exception) {
            HouseStatus.Error(e)
        }
    }
}