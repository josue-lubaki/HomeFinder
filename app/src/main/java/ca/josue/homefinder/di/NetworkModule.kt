package ca.josue.homefinder.di

import ca.josue.homefinder.data.local.db.HomeFinderDB
import ca.josue.homefinder.data.remote.HouseService
import ca.josue.homefinder.data.repository.house.datasource.HouseRemoteDataSource
import ca.josue.homefinder.data.repository.house.datasourceimpl.HouseRemoteDataSourceImpl
import ca.josue.homefinder.utils.Constants.BASE_URL
import com.google.gson.Gson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
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
    fun provideHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient : OkHttpClient) : Retrofit {
        val gson = Gson()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
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
    fun provideRemoteDataSource(
        houseService : HouseService,
        database: HomeFinderDB
    ) : HouseRemoteDataSource {
        return HouseRemoteDataSourceImpl(
            service = houseService,
            database = database
        )
    }
}