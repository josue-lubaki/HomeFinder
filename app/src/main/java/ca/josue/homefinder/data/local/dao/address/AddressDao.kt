package ca.josue.homefinder.data.local.dao.address

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.josue.homefinder.domain.models.Address

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

@Dao
interface AddressDao {

    @Query("SELECT * FROM address_table")
    fun getAllAddresses(): PagingSource<Int, Address>

//    @Query("SELECT * FROM owners_table")
//    fun getAllAddressesList(): String
//
//    val pager: Pager<String, String>
//        get() = Pager(
//            config = PagingConfig(
//                pageSize = 20,
//                enablePlaceholders = false,
//                maxSize = 100
//            ),
//            pagingSourceFactory = { StringKeyedPagingSource(
//                items = listOf(getAllAddressesList()),
//                getKey = { owner -> owner }
//            ) }
//        )

//    val getAllAddresses: Flow<PagingData<String>>
//        get() = pager.flow

    @Query("SELECT * FROM address_table WHERE id = :id")
    fun getAddressById(id: Int): Address

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: Address)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddresses(addresses: List<Address>)

    @Query("DELETE FROM address_table")
    suspend fun deleteAllAddresses()
}