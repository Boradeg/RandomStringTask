package com.app.randomstringtask.di

import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.Data.Repository.RandomStringRepositoryImpl
import com.app.randomstringtask.Data.local.RandomStringDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRandomStringRepository(
        dao: RandomStringDao
    ): RandomStringRepository {
        return RandomStringRepositoryImpl(dao)
    }
}