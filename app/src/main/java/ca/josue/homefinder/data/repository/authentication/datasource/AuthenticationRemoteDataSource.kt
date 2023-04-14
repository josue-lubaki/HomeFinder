package ca.josue.homefinder.data.repository.authentication.datasource

import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.models.AuthenticationStatus

/**
 * created by Josue Lubaki
 * date : 2023-04-09
 * version : 1.0.0
 */

interface AuthenticationRemoteDataSource {
    suspend fun login(request : Authentication) : AuthenticationStatus
}