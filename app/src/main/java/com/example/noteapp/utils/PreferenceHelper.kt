package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

class  PreferenceHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)

    var isOnBoardShown: Boolean
        get() = sharedPreferences.getBoolean("board", false)
        set(value) = sharedPreferences.edit().putBoolean("board", value).apply()
    var text:String?
        get()=sharedPreferences.getString("text", "")
        set(value) = sharedPreferences.edit()?.putString("text", value)!!.apply()
}
