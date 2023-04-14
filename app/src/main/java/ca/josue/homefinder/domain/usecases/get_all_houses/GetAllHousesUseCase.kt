package ca.josue.homefinder.domain.usecases.get_all_houses

import ca.josue.homefinder.data.repository.Repository
import ca.josue.homefinder.domain.models.HouseStatus

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

class GetAllHousesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): HouseStatus = repository.getAllHouses()
}