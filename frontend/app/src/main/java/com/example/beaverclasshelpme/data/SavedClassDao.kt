package com.example.beaverclasshelpme.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedClassDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myClass: SavedClass)

    @Delete
    suspend fun delete(myClass: SavedClass)

    @Query("SELECT * FROM SavedClass")
    fun getAllClasses(): Flow<List<SavedClass>>

    @Query("DELETE FROM SavedClass")
    suspend fun deleteAll()
}