package ca.josue.homefinder.presentation.login

import androidx.annotation.StringRes

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
    data class Error(@StringRes val message: Int) : LoginState()
}
