package ca.josue.homefinder.data.local.dao.owner

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.josue.homefinder.domain.models.Owner

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

@Dao
interface OwnerDao {

    @Query("SELECT * FROM owners_table")
    fun getAllOwners(): PagingSource<Int, Owner>

//    @Query("SELECT * FROM owners_table")
//    fun getAllOwnersList(): List<Owner>

//    val pager: Pager<String, Owner>
//        get() = Pager(
//            config = PagingConfig(
//                pageSize = 20,
//                enablePlaceholders = false,
//                maxSize = 100
//            ),
//            pagingSourceFactory = { StringKeyedPagingSource(
//                items = getAllOwnersList(),
//                getKey = { owner -> owner.id.toString() }
//            ) }
//        )
//
//    val getAllOwners: Flow<PagingData<Owner>>
//        get() = pager.flow

    @Query("SELECT * FROM owners_table WHERE id = :id")
    fun getOwnerById(id: Int): Owner

    @Query("SELECT * FROM owners_table WHERE username = :username")
    fun getOwnerByUsername(username: String): Owner

    @Query("SELECT * FROM owners_table WHERE email = :email")
    fun getOwnerByEmail(email: String): Owner

    @Query("DELETE FROM owners_table")
    suspend fun deleteAllOwners()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwner(owner: Owner)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwners(owners: List<Owner>)

}