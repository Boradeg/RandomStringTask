/*
package com.app.randomstringtask.ui.theme.Data


import androidx.room.*
import kotlinx.coroutines.flow.Flow
package com.app.randomstringtask.di

import android.content.Context
import androidx.room.Room
import com.app.randomstringtask.data.local.AppDatabase
import com.app.randomstringtask.data.local.RandomStringDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "random_string_db"
        ).build()
    }

    @Provides
    fun provideRandomStringDao(db: AppDatabase): RandomStringDao = db.randomStringDao()
}

@Dao
interface RandomStringDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(randomString: RandomStringEntity)

    @Query("SELECT * FROM random_strings")
    fun getAll(): Flow<List<RandomStringEntity>>

    @Delete
    suspend fun delete(randomString: RandomStringEntity)

    @Query("DELETE FROM random_strings")
    suspend fun deleteAll()
}

@Entity(tableName = "random_strings")
data class RandomStringEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value: String,
    val length: Int,
    val created: String
)
@Database(entities = [RandomStringEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun randomStringDao(): RandomStringDao
}*/
