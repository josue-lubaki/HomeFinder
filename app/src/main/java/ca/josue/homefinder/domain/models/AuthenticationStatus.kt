package ca.josue.homefinder.domain.models

import java.lang.Exception

sealed class AuthenticationStatus {
    object Success : AuthenticationStatus()
    data class Error(val exception: Exception) : AuthenticationStatus()
}
