package com.example.finsight.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finsight.R
import com.example.finsight.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteAdapter(
    private val items: MutableList<Note>,
    private val onEdit: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.noteTitle)
        val content: TextView = view.findViewById(R.id.noteContent)
        val date: TextView = view.findViewById(R.id.noteDate)
        val editBtn: ImageButton = view.findViewById(R.id.btnEdit)
        val delBtn: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return VH(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val note = items[position]
        holder.title.text = note.title
        holder.content.text = note.content

        val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        holder.date.text = formatter.format(Date(note.createdAt))

        holder.editBtn.setOnClickListener { onEdit(note) }
        holder.delBtn.setOnClickListener { onDelete(note) }
    }

    fun updateData(newList: List<Note>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}
