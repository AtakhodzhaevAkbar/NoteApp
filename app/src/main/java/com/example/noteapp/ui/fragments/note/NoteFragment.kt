package com.example.noteapp.ui.fragments.note

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.model.NoteModel
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.`interface`.onClickItem
import com.example.noteapp.ui.adapter.NoteAdapter

class NoteFragment : Fragment(), onClickItem {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentNoteBinding
    private var isGridLayout = false
    private var noteAdapter = NoteAdapter(this, this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        noteAdapter.setNavController(navController)
        binding.btnChangeLayout.setOnClickListener {
            isGridLayout = !isGridLayout
            changeRecyclerViewLayout()
        }
        setupListener()
        initialize()
        getData()
    }

    private fun getData() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }

    private fun initialize() {
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListener() {
        binding.btnChangeScreen.setOnClickListener {
            findNavController().navigate(
                R.id.action_noteFragment_to_noteDetailFragment,
                Bundle().apply {
                    putLong(
                        "noteId",
                        -1L
                    )
                }
            )
        }

    }

    private fun changeRecyclerViewLayout() {
        val layoutManager = if (isGridLayout) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }

        binding.homeRecyclerView.layoutManager = layoutManager
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить")
            setPositiveButton("Да") { dialog, which ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет") { dialog, which ->
                dialog.cancel()
            }
            show()
        }
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

}
