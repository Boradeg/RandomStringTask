package com.app.randomstringtask.ui.theme.Data


import android.app.Application
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.randomstringtask.ui.theme.Screen.RandomStringDisplay
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
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

@Dao
interface RandomStringDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RandomStringEntity)

    @Query("SELECT * FROM random_strings ORDER BY id DESC")
    fun observeAllRandomStrings(): Flow<List<RandomStringEntity>>

    @Delete
    suspend fun deleteRandomString(entity: RandomStringEntity)

    @Query("DELETE FROM random_strings")
    suspend fun deleteAll()
}
fun RandomStringDisplay.toEntity(): RandomStringEntity {
    return RandomStringEntity(
        value = this.value,
        length = this.length,
        created = this.created
    )
}



@Entity(tableName = "random_strings")
data class RandomStringEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value: String,
    val length: Int,
    val created: String
)
@Database(
    entities = [RandomStringEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun randomStringDao(): RandomStringDao
}




