package ca.josue.homefinder.utils

import ca.josue.homefinder.R
import java.net.UnknownHostException

/**
 * created by Josue Lubaki
 * date : 2023-05-03
 * version : 1.0.0
 */


sealed class HttpError {
    object UNAUTHORIZED : HttpError()
    object INTERNET_CONNECTION : HttpError()
    object INTERNAL_SERVER_ERROR : HttpError()
    object BAD_REQUEST : HttpError()
    object ACCESS_TOKEN_MISSING : HttpError()
    object HOUSE_NOT_FOUND : HttpError()
    object UNKNOWN : HttpError()

    fun getErrorMessage(): Int {
        return when (this) {
            UNAUTHORIZED -> R.string.error_unauthorized
            BAD_REQUEST -> R.string.bad_request_msg
            INTERNET_CONNECTION -> R.string.error_internet_connection
            ACCESS_TOKEN_MISSING -> R.string.error_access_token_missing
            HOUSE_NOT_FOUND -> R.string.error_house_not_found
            else -> R.string.error_unknown
        }
    }
}

fun handleException(exception: Exception) : HttpError {
    return when (exception) {
        is UnknownHostException -> HttpError.INTERNET_CONNECTION
        else -> HttpError.UNKNOWN
    }
}

fun handleErrorResponse(code: Int) : HttpError {
    return when (code) {
        401 -> HttpError.UNAUTHORIZED
        400 -> HttpError.BAD_REQUEST
        500 -> HttpError.INTERNAL_SERVER_ERROR
        else -> HttpError.UNKNOWN
    }
}