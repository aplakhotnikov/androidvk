package com.example.androidvk.di

import com.example.androidvk.data.AppDetailsMapper
import com.example.androidvk.data.ApplicationsApi
import com.example.androidvk.data.ApplicationsRepositoryImpl
import com.example.androidvk.domain.ApplicationsRepository
import com.example.androidvk.domain.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideOkHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    private val json = Json { ignoreUnknownKeys = true }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Constants.APPLICATIONS_API_BASE_URL)
            .client(client)
            .addConverterFactory(
                json
                    .asConverterFactory(
                        "application/json".toMediaType()
                    )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideApplicationsApi(retrofit: Retrofit): ApplicationsApi {
        return retrofit.create(ApplicationsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDetailsMapper(): AppDetailsMapper {
        return AppDetailsMapper()
    };

    @Singleton
    @Provides
    fun providesApplicationsRepository(applicationsApi: ApplicationsApi, appDetailsMapper: AppDetailsMapper): ApplicationsRepository {
        return ApplicationsRepositoryImpl(applicationsApi, appDetailsMapper);
    }
}