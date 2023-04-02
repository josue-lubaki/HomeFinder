package ca.josue.homefinder.presentation.house

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import ca.josue.homefinder.domain.models.House
import ca.josue.homefinder.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCase: UseCases
): ViewModel() {

    val getAllHeroes = useCase.getAllHousesUseCase()
}