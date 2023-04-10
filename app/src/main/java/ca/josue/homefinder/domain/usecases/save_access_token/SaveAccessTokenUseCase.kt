package ca.josue.homefinder.domain.usecases.save_access_token

import ca.josue.homefinder.data.repository.Repository

/**
 * created by Josue Lubaki
 * date : 2023-04-09
 * version : 1.0.0
 */

class SaveAccessTokenUseCase(private val repository: Repository) {
    suspend operator fun invoke(token: String) = repository.onSaveTokenAccess(token)
}