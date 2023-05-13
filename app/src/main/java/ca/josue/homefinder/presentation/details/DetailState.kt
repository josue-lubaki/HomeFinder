package ca.josue.homefinder.presentation.details

import androidx.annotation.StringRes
import ca.josue.homefinder.domain.models.House

sealed class DetailState {
    object Idle : DetailState()
    object Loading : DetailState()
    data class Success(val house: House) : DetailState()
    data class Error(@StringRes val message: Int) : DetailState()
}
