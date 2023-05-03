package ca.josue.homefinder.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.josue.homefinder.domain.models.Authentication
import ca.josue.homefinder.domain.models.AuthenticationStatus.Error
import ca.josue.homefinder.domain.models.AuthenticationStatus.Success
import ca.josue.homefinder.domain.usecases.login_user.LoginUseCase
import ca.josue.homefinder.utils.HttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dispatchers: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private sealed class LoginAction {
        data class Login(val request: Authentication) : LoginAction()
        object LoginSuccess : LoginAction()
        data class LoginError(val error: HttpError) : LoginAction()
    }

    private fun dispatchAction(action: LoginAction) {
        when (action) {
            is LoginAction.Login -> action.reduce()
            is LoginAction.LoginSuccess -> action.reduce()
            is LoginAction.LoginError -> action.reduce()
        }
    }

    private fun LoginAction.Login.reduce() {
        _state.value = LoginState.Loading
        viewModelScope.launch(dispatchers) {
            when (val loginStatus = loginUseCase(request)) {
                is Success -> dispatchAction(LoginAction.LoginSuccess)
                is Error -> dispatchAction(LoginAction.LoginError(loginStatus.exception))
            }
        }
    }

    private fun LoginAction.LoginSuccess.reduce() {
        _state.value = LoginState.Success
    }

    private fun LoginAction.LoginError.reduce() {
        _state.value = LoginState.Error(error.getErrorMessage())
    }

    fun onLogin(username: String, password: String) {
        val request = Authentication(username, password)
        dispatchAction(LoginAction.Login(request))
    }
}