package com.example.noteapp

import android.app.Application
import androidx.room.Room
import com.example.noteapp.data.db.AppDatabase
import com.example.noteapp.utils.PreferenceHelper

class App : Application() {
    companion object {
        var appDatabase: AppDatabase? = null

    }
    fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper(this)
        getInstance()
    }

}
