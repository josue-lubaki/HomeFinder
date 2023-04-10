package ca.josue.homefinder.domain.usecases.login_user

import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.repository.AuthenticationRepository
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-04-09
 * version : 1.0.0
 */

class LoginUseCase @Inject constructor(
    private val repository: AuthenticationRepository
){
    suspend operator fun invoke(request : Authentication) = repository.login(request)
}