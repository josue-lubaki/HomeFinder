package ca.josue.homefinder.domain.models

/**
 * created by Josue Lubaki
 * date : 2023-04-23
 * version : 1.0.0
 */

sealed class HouseLocalStatus : Status {
    data class Success(val house: House) : HouseLocalStatus()
    data class Error(val exception: Exception) : HouseLocalStatus()
}