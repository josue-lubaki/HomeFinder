package ca.josue.homefinder.data.models.owner

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseOwner(
    val data: List<DataOwner>,
    val lastUpdated: Long,
    val message: String,
    val nextPage: Int,
    val prevPage: Int?,
    val success: Boolean
)

@Serializable
data class DataOwner(
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val username: String
)