package com.aryan.taskmanager.dependency_injection

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.aryan.taskmanager.data.api.ServerAPI
import com.aryan.taskmanager.data.api.repository.SeverRepository
import com.aryan.taskmanager.data.api.repository.SeverRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(): ServerAPI {
        return Retrofit.Builder()
            .baseUrl("http://192.168.212.217:8050/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ServerAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs",MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: ServerAPI, prefs: SharedPreferences): SeverRepository {
        return SeverRepositoryImpl(api, prefs)
    }

}