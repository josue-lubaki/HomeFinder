package ca.josue.homefinder.domain.usecases.read_onboarding

import ca.josue.homefinder.data.repository.Repository
import kotlinx.coroutines.flow.Flow

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke() : Flow<Boolean> = repository.onReadOnBoardingState()
}