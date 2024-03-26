package com.example.noteapp.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.model.NoteModel
import com.example.noteapp.databinding.ItemNoteBinding
import com.example.noteapp.`interface`.onClickItem
import com.example.noteapp.ui.fragments.note.NoteFragmentDirections

class NoteAdapter(private val onLongClick: onClickItem, private val onClick: onClickItem) :
    ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallBack()) {
    private var controller: NavController? = null
    fun setNavController(navController: NavController) {
        this.controller = navController
    }


    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.titleTextView.text = item.title
            binding.descriptionTextView.text = item.description
            binding.dateTextView.text = item.date
            binding.timeTextView.text = item.time
            Log.d("NoteAdapter", "Color: ${item.color}")

            try {
                val color = Color.parseColor(item.color)
                binding.root.setBackgroundColor(color)
            } catch (e: IllegalArgumentException) {
                Log.e("NoteAdapter", "Invalid color: ${item.color}")
                binding.root.setBackgroundColor(Color.WHITE)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(position))
            true
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
            }
        }

    }

    class DiffCallBack : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
    }
