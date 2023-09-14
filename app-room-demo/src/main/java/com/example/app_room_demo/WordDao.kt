package com.example.app_room_demo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao {

    @Insert
    suspend fun insert(word: Word)

    @Update
    fun update(word: Word)

    @Delete
    fun delete(word: Word)

    @Query("DELETE FROM word")
    fun deleteAllWords()

    @Query("SELECT * FROM word ORDER BY id DESC")
    fun getAll(): LiveData<List<Word>>
}