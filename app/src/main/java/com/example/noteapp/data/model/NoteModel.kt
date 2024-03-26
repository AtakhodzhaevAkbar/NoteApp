package com.example.noteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val description: String,
    val time: String,
    val date: String,
    val color: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    // Конструктор, который принимает id и остальные свойства
    constructor(id: Long, title: String, description: String, time: String, date: String, color: String) : this(title, description, time, date, color) {
        this.id = id
    }
}
