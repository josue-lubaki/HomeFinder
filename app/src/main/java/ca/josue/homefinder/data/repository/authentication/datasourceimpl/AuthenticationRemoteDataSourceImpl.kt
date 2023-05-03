package ca.josue.homefinder.data.repository.authentication.datasourceimpl

import ca.josue.homefinder.data.remote.AuthenticationService
import ca.josue.homefinder.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.models.AuthenticationStatus
import ca.josue.homefinder.domain.usecases.UseCases
import ca.josue.homefinder.utils.handleErrorResponse
import ca.josue.homefinder.utils.handleException
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-04-09
 * version : 1.0.0
 */

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val service: AuthenticationService,
    private val useCases: UseCases
) : AuthenticationRemoteDataSource {
    override suspend fun login(request: Authentication): AuthenticationStatus {
        val response = service.login(request)
        return try {
            if (response.isSuccessful) {
                val token = response.body()!!.token
                useCases.saveAccessTokenUseCase(token)
                AuthenticationStatus.Success
            } else {
                AuthenticationStatus.Error(handleErrorResponse(response.code()))
            }
        } catch (e: Exception) {
            AuthenticationStatus.Error(handleException(e))
        }
    }
}