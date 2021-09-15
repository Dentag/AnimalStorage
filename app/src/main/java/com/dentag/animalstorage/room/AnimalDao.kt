package com.dentag.animalstorage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {

    @Query("SELECT * FROM animal")
    fun getAll(): Flow<List<Animal>>

    @Query("SELECT * FROM animal")
    fun getAllOnce(): List<Animal>

    @Insert
    suspend fun insert(animal: Animal)

    @Delete
    suspend fun delete(animal: Animal)

    @Update
    suspend fun update(animal: Animal)
}
