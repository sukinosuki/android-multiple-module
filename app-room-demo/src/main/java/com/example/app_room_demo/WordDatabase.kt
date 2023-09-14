package com.example.app_room_demo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao
}