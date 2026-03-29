package com.example.androidvk.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDetailsDao {
    @Query("SELECT * FROM app_details WHERE id = :id")
    fun getAppDetails(id: String): AppDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppDetails(appDetails: AppDetailsEntity)
}