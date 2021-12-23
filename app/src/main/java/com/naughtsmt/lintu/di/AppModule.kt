package com.naughtsmt.lintu.di

import com.naughtsmt.lintu.common.Constants.BASE_URL
import com.naughtsmt.lintu.data.data_source.BoardGameAtlasApi
import com.naughtsmt.lintu.data.repository.GameRepositoryImpl
import com.naughtsmt.lintu.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesBGAApi(): BoardGameAtlasApi {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(BoardGameAtlasApi::class.java)
    }


    @Provides
    @Singleton
    fun providesGameRepository(api: BoardGameAtlasApi): GameRepository {
        return GameRepositoryImpl(api)
    }
}