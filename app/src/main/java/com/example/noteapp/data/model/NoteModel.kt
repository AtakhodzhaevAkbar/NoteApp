package com.example.noteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "noteModel")
data class  NoteModel(
    val title: String,
    val description: String,
    val time: String,
    val date: String,
    val color:String,
) {
  @PrimaryKey(autoGenerate = true)
   var id: Int = 0
}
