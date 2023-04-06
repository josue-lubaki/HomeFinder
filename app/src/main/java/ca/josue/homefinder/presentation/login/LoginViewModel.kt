package ca.josue.homefinder.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state : StateFlow<LoginState> = _state.asStateFlow()

    // TODO : Implement LoginViewModel
}