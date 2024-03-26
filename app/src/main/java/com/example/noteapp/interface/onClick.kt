package com.example.noteapp.`interface`

import com.example.noteapp.data.model.NoteModel

interface onClickItem {
    fun onLongClick(noteModel: NoteModel)
    fun onClick(noteModel: NoteModel)
}