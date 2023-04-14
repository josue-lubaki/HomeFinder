package ca.josue.homefinder.login.data.repository.datasourceimpl

import ca.josue.homefinder.data.models.authentication.AuthenticationResponse
import ca.josue.homefinder.data.remote.AuthenticationService
import ca.josue.homefinder.data.repository.authentication.datasourceimpl.AuthenticationRemoteDataSourceImpl
import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.models.AuthenticationStatus
import ca.josue.homefinder.domain.usecases.UseCases
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class LoginRemoteDataSourceImplTest {

    private val authenticationServiceMock = mockk<AuthenticationService>()
    private val useCasesMock = mockk<UseCases>()

    private val underTest = AuthenticationRemoteDataSourceImpl(
        service = authenticationServiceMock,
        useCases = useCasesMock
    )

    @Test
    fun testLoginSuccessful() = runTest {
        // Given
        val user = Authentication(username = "test@test.com", password = "password")
        val expectedResponse = Response.success(AuthenticationResponse(token = "token"))

        coEvery { authenticationServiceMock.login(any()) } returns expectedResponse
        coEvery { useCasesMock.saveAccessTokenUseCase(any()) } returns Unit

        // When
        val result = underTest.login(user)

        // Then
        assertTrue(result is AuthenticationStatus.Success)
    }

    @Test
    fun testLoginFailed() = runTest {
        // Given
        val user = Authentication(username = "test@test.com", password = "password")
        val expectedResponse = Response.error<AuthenticationResponse>(
            401, "".toResponseBody("application/json".toMediaTypeOrNull())
        )

        coEvery { authenticationServiceMock.login(any()) } returns expectedResponse

        // When
        val result = underTest.login(user)

        // Then
        assertTrue(result is AuthenticationStatus.Error)
    }
}