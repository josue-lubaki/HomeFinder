package ca.josue.homefinder.presentation.details

import ca.josue.homefinder.domain.models.House

sealed class DetailState {
    object Idle : DetailState()
    object Loading : DetailState()
    data class Success(val house: House) : DetailState()
    data class Error(val message: String?) : DetailState()
}
