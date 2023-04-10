package ca.josue.homefinder.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed : Boolean)
    fun readOnBoardingState() : Flow<Boolean>
    fun readTokenAccess(): Flow<String>
    suspend fun saveTokenAccess(token: String)
}