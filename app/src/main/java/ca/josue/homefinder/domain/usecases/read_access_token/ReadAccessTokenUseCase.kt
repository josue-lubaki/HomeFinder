package ca.josue.homefinder.domain.usecases.read_access_token

import ca.josue.homefinder.domain.repository.DataStoreOperations
import javax.inject.Inject

/**
 * created by Josue Lubaki
 * date : 2023-04-09
 * version : 1.0.0
 */

class ReadAccessTokenUseCase @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) {
    operator fun invoke() = dataStoreOperations.readTokenAccess()
}