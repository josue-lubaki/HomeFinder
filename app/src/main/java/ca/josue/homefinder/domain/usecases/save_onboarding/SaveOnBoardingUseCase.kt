package ca.josue.homefinder.domain.usecases.save_onboarding

import ca.josue.homefinder.data.repository.Repository

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.onSaveBoardingState(completed)
    }
}