package ca.josue.homefinder.domain.usecases

import ca.josue.homefinder.domain.usecases.get_all_houses.GetAllHousesUseCase
import ca.josue.homefinder.domain.usecases.get_house_details.GetHouseDetailsUseCase
import ca.josue.homefinder.domain.usecases.read_onboarding.ReadOnBoardingUseCase
import ca.josue.homefinder.domain.usecases.save_access_token.SaveAccessTokenUseCase
import ca.josue.homefinder.domain.usecases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHousesUseCase: GetAllHousesUseCase,
    val getHouseDetailsUseCase: GetHouseDetailsUseCase,
    val saveAccessTokenUseCase: SaveAccessTokenUseCase,
)
