package com.nakhli.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nakhli.data.db.entity.*
import com.nakhli.data.db.dao.NewsDao

const val DATABASE_VERSION = 2

@Database(
    entities = [StoryEntity::class, VideoEntity::class, SportEntity::class],
    version = DATABASE_VERSION
)
/**
 * Database Class implementation
 *
 */
abstract class EuroSportDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}