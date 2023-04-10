package ca.josue.homefinder.data.remote

import ca.josue.homefinder.data.models.house.ApiResponseHouse
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

/**
 * created by Josue Lubaki
 * date : 2023-03-25
 * version : 1.0.0
 */

interface HouseService {

    @GET("/api/v1/houses")
    suspend fun getAllHouses(
        @Query("page") page : Int = 1,
        @HeaderMap headers: Map<String, String>
    ): ApiResponseHouse
}