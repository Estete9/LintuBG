package com.naughtsmt.lintu.di

import com.naughtsmt.lintu.common.Constants.BASE_URL
import com.naughtsmt.lintu.data.data_source.BoardGameAtlasApi
import com.naughtsmt.lintu.data.repository.GameRepositoryImpl
import com.naughtsmt.lintu.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesBGAApi(): BoardGameAtlasApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BoardGameAtlasApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGameRepository(api: BoardGameAtlasApi): GameRepository {
        return GameRepositoryImpl(api)
    }
}