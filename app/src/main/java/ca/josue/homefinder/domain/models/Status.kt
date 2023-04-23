package ca.josue.homefinder.domain.models

sealed interface Status {
    data class Success(val data: Any) : Status
    data class Error(val exception: Exception) : Status
}