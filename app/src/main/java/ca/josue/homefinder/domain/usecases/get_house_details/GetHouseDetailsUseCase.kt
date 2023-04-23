package ca.josue.homefinder.domain.usecases.get_house_details

import ca.josue.homefinder.data.repository.Repository

/**
 * created by Josue Lubaki
 * date : 2023-04-23
 * version : 1.0.0
 */

class GetHouseDetailsUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(houseId: Int) = repository.getSelectedHouse(houseId)
}