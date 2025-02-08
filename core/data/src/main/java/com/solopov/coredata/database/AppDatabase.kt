package com.solopov.coredata.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.solopov.coredata.database.dao.FavoriteDao
import com.solopov.coredata.database.entity.FavoriteEntity

private const val DATABASE_NAME = "stylo.db"

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun newInstance(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun favoriteDao(): FavoriteDao
}
