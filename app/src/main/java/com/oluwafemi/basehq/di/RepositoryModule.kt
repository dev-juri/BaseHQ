package com.oluwafemi.basehq.di


import com.oluwafemi.basehq.data.local.BaseDAO
import com.oluwafemi.basehq.data.remote.BaseService
import com.oluwafemi.basehq.data.repository.Repository
import com.oluwafemi.basehq.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        baseService: BaseService,
        local: BaseDAO,
        dispatcher: CoroutineDispatcher
    ): Repository =
        RepositoryImpl(baseService, local, dispatcher)

}