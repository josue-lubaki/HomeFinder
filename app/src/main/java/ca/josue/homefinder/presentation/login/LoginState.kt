package ca.josue.homefinder.presentation.login

import androidx.annotation.StringRes

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(@StringRes val message: Int) : LoginState()
}
