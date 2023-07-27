package com.example.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

//val Context.dataStore by preferen
// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings",

    // 从shared preferences 迁移
//    produceMigrations = { context ->
//        listOf(SharedPreferencesMigration(context, "sp_file_name"))
//    }
)