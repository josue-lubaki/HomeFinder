package ca.josue.homefinder.domain.usecases.update_house_like

import ca.josue.homefinder.data.repository.Repository

/**
 * created by Josue Lubaki
 * date : 2023-04-25
 * version : 1.0.0
 */

class UpdateHouseLikeUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(uuid: Int, isLiked: Boolean) = repository.updateHouseLike(uuid, isLiked)
}