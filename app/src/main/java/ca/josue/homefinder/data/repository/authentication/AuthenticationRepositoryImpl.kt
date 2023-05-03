package ca.josue.homefinder.data.repository.authentication

import ca.josue.homefinder.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.models.AuthenticationStatus
import ca.josue.homefinder.domain.repository.AuthenticationRepository
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-04-09
 * version : 1.0.0
 */

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {
    override suspend fun login(request: Authentication): AuthenticationStatus {
        return remoteDataSource.login(request)
    }
}