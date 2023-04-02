package ca.josue.homefinder.data.models.address

import kotlinx.serialization.Serializable

@Serializable
class ApiResponseAddress : ArrayList<ApiResponseAddressItem>()

@Serializable
data class ApiResponseAddressItem(
    val city: String,
    val country: String,
    val id: String,
    val number: String,
    val postalCode: String,
    val province: String,
    val street: String
)