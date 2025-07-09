package com.app.randomstringtask.Data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

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
