package com.app.randomstringtask.Data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RandomStringEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun randomStringDao(): RandomStringDao
}