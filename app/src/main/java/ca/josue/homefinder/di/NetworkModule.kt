package ca.josue.homefinder.di

import ca.josue.homefinder.BuildConfig
import ca.josue.homefinder.data.local.db.HomeFinderDB
import ca.josue.homefinder.data.remote.AuthenticationService
import ca.josue.homefinder.data.remote.HouseService
import ca.josue.homefinder.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import ca.josue.homefinder.data.repository.authentication.datasourceimpl.AuthenticationRemoteDataSourceImpl
import ca.josue.homefinder.data.repository.house.datasource.HouseRemoteDataSource
import ca.josue.homefinder.data.repository.house.datasourceimpl.HouseRemoteDataSourceImpl
import ca.josue.homefinder.domain.usecases.UseCases
import ca.josue.homefinder.domain.usecases.read_access_token.ReadAccessTokenUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @OptIn(DelicateCoroutinesApi::class)
    @Provides
    @Singleton
    fun provideHttpClient(
        readAccessTokenUseCase: ReadAccessTokenUseCase,
        dispatchers: CoroutineDispatcher
    ) : OkHttpClient {

        var tokenAccess : String? = null

        GlobalScope.launch(dispatchers) {
             tokenAccess = readAccessTokenUseCase()
                .stateIn(this)
                .value
        }

        val interceptor = okhttp3.Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $tokenAccess")
                .build()
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient : OkHttpClient) : Retrofit {
        val gson = Gson()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideHouseService(retrofit: Retrofit) : HouseService {
        return retrofit.create(HouseService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(retrofit: Retrofit) : AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        houseService : HouseService,
        database: HomeFinderDB,
        readAccessTokenUseCase: ReadAccessTokenUseCase
    ) : HouseRemoteDataSource {
        return HouseRemoteDataSourceImpl(
            service = houseService,
            database = database,
            readAccessTokenUseCase = readAccessTokenUseCase
        )
    }

    @Provides
    @Singleton
    fun provideAuthenticationRemoteDataSource(
        authenticationService : AuthenticationService,
        useCases: UseCases
    ) : AuthenticationRemoteDataSource {
        return AuthenticationRemoteDataSourceImpl(
            service = authenticationService,
            useCases = useCases
        )
    }

    @Provides
    @Singleton
    fun provideDispatcher() : CoroutineDispatcher = Dispatchers.IO
}