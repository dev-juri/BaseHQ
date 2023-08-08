package com.oluwafemi.basehq.di

import android.app.Application
import androidx.room.Room
import com.oluwafemi.basehq.data.local.BaseDAO
import com.oluwafemi.basehq.data.local.BaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): BaseDatabase =
        Room.databaseBuilder(application, BaseDatabase::class.java, "Base.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideBaseDAO(database: BaseDatabase): BaseDAO = database.baseDAO
}