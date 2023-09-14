package com.example.app_room_demo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room


class WordRepository private constructor(context: Context) {

    companion object {
        private var instance: WordRepository? = null
        fun getInstance(context: Context): WordRepository {
            if (instance == null) {
                instance = WordRepository(context)
            }

            return instance!!
        }

        operator fun invoke(context: Context): WordRepository {
            Log.i(TAG, "invoke: $instance")
            if (instance == null) {
                instance = WordRepository(context)
            }

            return instance!!
        }
    }

    private val wordDatabase by lazy {
        Room.databaseBuilder(context, WordDatabase::class.java, "word_database").build()
    }

    private val wordDao by lazy {
        wordDatabase.getWordDao()
    }

    fun getAllWords(): LiveData<List<Word>> {
        return wordDao.getAll()
    }

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}