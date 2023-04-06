package ca.josue.homefinder.di

import android.content.Context
import ca.josue.homefinder.data.repository.Repository
import ca.josue.homefinder.data.repository.onboarding.DataStoreOperationsImpl
import ca.josue.homefinder.domain.repository.DataStoreOperations
import ca.josue.homefinder.domain.usecases.UseCases
import ca.josue.homefinder.domain.usecases.get_all_houses.GetAllHousesUseCase
import ca.josue.homefinder.domain.usecases.read_onboarding.ReadOnBoardingUseCase
import ca.josue.homefinder.domain.usecases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context : Context) : DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository) : UseCases {
        return UseCases(
            getAllHousesUseCase = GetAllHousesUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository)
        )
    }
}