package ca.josue.homefinder.login.domain.usecases

import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.models.AuthenticationStatus
import ca.josue.homefinder.domain.repository.AuthenticationRepository
import ca.josue.homefinder.domain.usecases.login_user.LoginUseCase
import ca.josue.homefinder.utils.HttpError
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUseCaseTest {

    private val loginRepository: AuthenticationRepository = mockk()
    private val loginUseCase = LoginUseCase(loginRepository)

    @Test
    fun `invoke should return Success when login is successful`() = runTest {
        // Given
        val user = Authentication(username = "test@test.com", password = "password")
        val loginStatus = AuthenticationStatus.Success
        coEvery { loginRepository.login(user) } returns loginStatus

        // When
        val result = loginUseCase(user)

        // Then
        coVerify { loginRepository.login(user) }
        assertEquals(loginStatus, result)
    }

    @Test
    fun `invoke should return Error when login fails`() = runTest {
        // Given
        val user = Authentication(username = "test@test.com", password = "password")
        val exception = HttpError.BAD_REQUEST
        val loginStatus = AuthenticationStatus.Error(exception)
        coEvery { loginRepository.login(user) } returns loginStatus

        // When
        val result = loginUseCase(user)

        // Then
        coVerify { loginRepository.login(user) }
        assertEquals(
            loginStatus.exception.getErrorMessage(),
            (result as AuthenticationStatus.Error).exception.getErrorMessage()
        )
    }
}