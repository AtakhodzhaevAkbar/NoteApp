package com.example.noteapp.ui.fragments.note

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.model.NoteModel
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {
    private lateinit var binding: FragmentNoteDetailBinding
    private var selectedColor: Int = 0
    private var noteId: Long = -1L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteId = arguments?.getLong("noteId", -1L) ?: -1L
        if (noteId != -1L) {
            val existingNote = App().getInstance()?.noteDao()?.getNoteById(noteId)
            existingNote?.let { note ->
                binding.titleET.setText(note.title)
                binding.descriptionET.setText(note.description)
                selectedColor = Color.parseColor(note.color)
            }
        }
        binding.colorSelectionRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnColorGray -> selectedColor = Color.parseColor("#191818")
                R.id.btnColorWhite -> selectedColor = Color.parseColor("#EBE4C9")
                R.id.btnColorBrown -> selectedColor = Color.parseColor("#571818")
                else -> selectedColor = Color.BLACK
            }
        }

        val currentDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        binding.timeTV.text = currentTime
        binding.dateTV.text = currentDate

        binding.returnBtn.setOnClickListener {
            findNavController().navigate(NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteFragment())
        }

        binding.doneBtn.setOnClickListener {
            val title = binding.titleET.text.toString()
            val description = binding.descriptionET.text.toString()
            val time = binding.timeTV.text.toString()
            val date = binding.dateTV.text.toString()
            val color = if (selectedColor != 0) "#" + Integer.toHexString(selectedColor)
                .substring(2) else "#000000"

            if (noteId != -1L) {
                val updatedNote = NoteModel(
                    title = title,
                    description = description,
                    time = time,
                    date = date,
                    color = color
                )
                updatedNote.id = noteId.toLong()
                App().getInstance()?.noteDao()?.updateNote(updatedNote)
            } else {
                App().getInstance()?.noteDao()?.insertNote(
                    NoteModel(
                        title, description, time, date, color
                    )
                )
            }

            findNavController().navigate(R.id.action_noteDetailFragment_to_noteFragment)
        }
    }
}
