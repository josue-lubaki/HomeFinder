package ca.josue.homefinder.domain.models

import ca.josue.homefinder.utils.HttpError

sealed class AuthenticationStatus {
    object Success : AuthenticationStatus()
    data class Error(val exception: HttpError) : AuthenticationStatus()
}
