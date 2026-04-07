package com.example.androidvk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [AppDetailsEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDetailsDao(): AppDetailsDao

    companion object {
        const val DATABASE_NAME = "app_database"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE app_details ADD COLUMN isInWishlist INTEGER NOT NULL DEFAULT 0"
                )
            }
        }
    }
}