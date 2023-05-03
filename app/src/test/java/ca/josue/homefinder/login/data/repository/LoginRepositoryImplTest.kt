package ca.josue.homefinder.login.data.repository

import ca.josue.homefinder.data.repository.authentication.AuthenticationRepositoryImpl
import ca.josue.homefinder.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.models.AuthenticationStatus
import ca.josue.homefinder.utils.HttpError
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginRepositoryTest {
    private val authenticationRemoteDataSource = mockk<AuthenticationRemoteDataSource>()
    private val underTest = AuthenticationRepositoryImpl(authenticationRemoteDataSource)

    @Test
    fun `login() returns success`() = runTest {
        // given
        val user = Authentication(username = "test@test.com", password = "password")
        val expectedStatus = AuthenticationStatus.Success
        coEvery { authenticationRemoteDataSource.login(user) } returns expectedStatus

        // when
        val actualStatus = underTest.login(user)

        // then
        assertEquals(expectedStatus, actualStatus)
    }

    @Test
    fun `login() returns error`() = runTest {
        // given
        val user = Authentication(username = "test@test.com", password = "password")
        val expectedStatus = AuthenticationStatus.Error(HttpError.UNKNOWN)
        coEvery { authenticationRemoteDataSource.login(user) } returns expectedStatus

        // when
        val actualStatus = underTest.login(user)

        // then
        assertEquals(expectedStatus, actualStatus)
    }
}