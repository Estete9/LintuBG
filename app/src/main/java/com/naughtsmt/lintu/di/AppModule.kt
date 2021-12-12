package com.naughtsmt.lintu.di

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.common.Constants.BASE_URL
import com.naughtsmt.lintu.data.data_source.BoardGameAtlasApi
import com.naughtsmt.lintu.data.repository.GameRepositoryImpl
import com.naughtsmt.lintu.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesBGAApi(): BoardGameAtlasApi {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

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

//    @Provides
//    @Singleton
//    fun providesContext(@ApplicationContext context: Context): Context {
//        return context
//    }

//    @Provides
//    @Singleton
//    fun providesCode(@ApplicationContext application: Context): String? {
//        val intentWeb = Intent(
//            Intent.ACTION_VIEW,
//            Uri.parse(Constants.URL_TEXT)
//        )
//        intentWeb.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        application.startActivity(intentWeb)
//        val intent = Intent()
//        val data: Uri? = intent.data
//        val action: String? = intent.action
//        val code = if (data != null && data.toString().startsWith(Constants.URL_TEXT)) {
//            data.getQueryParameter("code")
//        } else ""
//
//        return code
//    }
}