package ca.josue.homefinder.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ca.josue.homefinder.data.local.db.HomeFinderDB
import ca.josue.homefinder.data.mapper.toDomain
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.data.remote.HouseService
import ca.josue.homefinder.domain.models.HouseRemoteKeys
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

@OptIn(ExperimentalPagingApi::class)
class HouseRemoteMediator @Inject constructor(
    private val service : HouseService,
    private val database: HomeFinderDB,
) : RemoteMediator<Int, House>() {

    private val houseDao = database.houseDao()
    private val houseRemoteKeysDao = database.houseRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTimestamp = System.currentTimeMillis()
        val lastUpdated = houseRemoteKeysDao.getLastUpdatedRemoteKeys()?.lastUpdated ?: 0
        val cacheTimeOut = 30 // 30 minutes

        val cachedExpired = (currentTimestamp - lastUpdated) > (cacheTimeOut * 60 * 1000)

        return if (cachedExpired) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, House>): HouseRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.uuid?.let { houseUuid ->
                houseRemoteKeysDao.getRemoteKeysById(houseUuid)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, House>): HouseRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { house ->
                houseRemoteKeysDao.getRemoteKeysById(uuid = house.uuid)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, House>): HouseRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { house ->
                houseRemoteKeysDao.getRemoteKeysById(uuid = house.uuid)
            }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, House>
    ): MediatorResult {
        try {
            val page = when(loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            val token = null // TODO: get token from shared preferences

            val headers = mapOf(
                "Authorization" to "Bearer $token"
            )
            val response = service.getAllHouses(
                page = page,
                headers = headers
            )

            if(response.data.isNotEmpty()){
                database.withTransaction {
                    if(loadType == LoadType.REFRESH){
                        houseDao.deleteAllHouses()
                        houseRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevKey = response.prevPage
                    val nextKey = response.nextPage
                    val keys = response.data.map {
                        HouseRemoteKeys(
                            uuid = it.uuid,
                            prevPage = prevKey,
                            nextPage = nextKey,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    houseRemoteKeysDao.insertAllRemoteKeys(remoteKeys = keys)
                    houseDao.insertHouses(houses = response.data.toDomain())
                }
            }
            return MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }
}