package com.app.randomstringtask.di

import android.app.Application
import androidx.room.Room
import com.app.randomstringtask.Data.local.AppDatabase
import com.app.randomstringtask.Data.local.RandomStringDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "random_string_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRandomStringDao(db: AppDatabase): RandomStringDao {
        return db.randomStringDao()
    }
}