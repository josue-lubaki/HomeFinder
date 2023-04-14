package ca.josue.homefinder.login.presentation

import ca.josue.homefinder.TestDispatcherRule
import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.models.AuthenticationStatus
import ca.josue.homefinder.domain.usecases.login_user.LoginUseCase
import ca.josue.homefinder.presentation.login.LoginState
import ca.josue.homefinder.presentation.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    // Create a mock of LoginUseCase
    private val mockLoginUseCase = mockk<LoginUseCase>()

    // Create a LoginViewModel instance
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(
            loginUseCase = mockLoginUseCase,
            dispatchers = testDispatcherRule.testDispatcher
        )
    }

    @Test
    fun testInitialState() = runTest {
        assertEquals(LoginState.Idle, loginViewModel.state.value)
    }

    @Test
    fun `test onLoginClicked sends Loading state`() = runTest {
        // When
        loginViewModel.onLogin("test@example.com", "password")

        // Then
        assertEquals(LoginState.Loading, loginViewModel.state.value)
    }

    @Test
    fun `test onLoginClicked sends Success state`() = runTest {
        // Given
        val email = "test@example.com"
        val password = "password"
        val expectedStatus = AuthenticationStatus.Success
        coEvery { mockLoginUseCase(Authentication(email, password)) } returns expectedStatus

        // When
        loginViewModel.onLogin(email, password)

        // Then
        assertEquals(LoginState.Success, loginViewModel.state.value)
    }

    @Test
    fun `test onLoginClicked sends Error state`() = runTest {
        // Given
        val expectedStatus = AuthenticationStatus.Error(Exception("Error"))
        coEvery { mockLoginUseCase.invoke(any()) } returns expectedStatus

        // When
        loginViewModel.onLogin(
            username = "test@example.com",
            password = "password"
        )

        // Then
        assertEquals(
            LoginState.Error(Exception("Error")).javaClass,
            loginViewModel.state.value.javaClass
        )
        assertEquals(
            expectedStatus.exception.message,
            (loginViewModel.state.value as LoginState.Error).exception.message
        )
    }

}