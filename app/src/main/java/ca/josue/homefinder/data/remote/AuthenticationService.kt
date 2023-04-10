package ca.josue.homefinder.data.remote

import ca.josue.homefinder.data.models.authentication.AuthenticationResponse
import ca.josue.homefinder.domain.models.Authentication
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * created by Josue Lubaki
 * date : 2023-04-09
 * version : 1.0.0
 */

interface AuthenticationService {

    @POST("/auth/login")
    suspend fun login(
        @Body request : Authentication
    ) : Response<AuthenticationResponse>
}