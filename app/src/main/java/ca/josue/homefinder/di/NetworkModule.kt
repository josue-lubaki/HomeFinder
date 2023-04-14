package ca.josue.homefinder.di

import ca.josue.homefinder.BuildConfig
import ca.josue.homefinder.data.local.db.HomeFinderDB
import ca.josue.homefinder.data.remote.AuthenticationService
import ca.josue.homefinder.data.remote.HouseService
import ca.josue.homefinder.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import ca.josue.homefinder.data.repository.authentication.datasourceimpl.AuthenticationRemoteDataSourceImpl
import ca.josue.homefinder.data.repository.house.datasource.HouseRemoteDataSource
import ca.josue.homefinder.data.repository.house.datasourceimpl.HouseRemoteDataSourceImpl
import ca.josue.homefinder.domain.repository.DataStoreOperations
import ca.josue.homefinder.domain.usecases.UseCases
import ca.josue.homefinder.network.AuthInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(dataStoreOperations: DataStoreOperations): Interceptor {
        return AuthInterceptor(dataStoreOperations)
    }

    @Provides
    @Singleton
    fun provideHttpClient(authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val gson = Gson()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideHouseService(retrofit: Retrofit): HouseService {
        return retrofit.create(HouseService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        houseService: HouseService,
        database: HomeFinderDB,
    ): HouseRemoteDataSource {
        return HouseRemoteDataSourceImpl(
            service = houseService,
            database = database,
        )
    }

    @Provides
    @Singleton
    fun provideAuthenticationRemoteDataSource(
        authenticationService: AuthenticationService,
        useCases: UseCases
    ): AuthenticationRemoteDataSource {
        return AuthenticationRemoteDataSourceImpl(
            service = authenticationService,
            useCases = useCases
        )
    }

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}