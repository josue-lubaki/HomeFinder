package ca.josue.homefinder.data.models.house

import ca.josue.homefinder.domain.models.Address
import ca.josue.homefinder.domain.models.Owner
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseHouse(
    val data: List<DataHouse>,
    val lastUpdated: Long? = null,
    val message: String? = null,
    val nextPage: Int? = null,
    val prevPage: Int? = null,
    val success: Boolean
)

@Serializable
data class DataHouse(
    val id: String,
    val uuid: Int,
    val description: String,
    val images: List<String>,
    val price: Long,
    val address: Address,
    val bedrooms: Int,
    val bathrooms: Int,
    val area: Int,
    val type: String,
    val yearBuilt: Int,
    val pool: Boolean,
    val owner: Owner
)