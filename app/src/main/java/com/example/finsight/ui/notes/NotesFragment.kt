package com.example.finsight.ui.notes

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finsight.R
import com.example.finsight.adapter.NoteAdapter
import com.example.finsight.model.Note
import com.example.finsight.repo.NoteRepository

class NotesFragment : Fragment() {
    private lateinit var adapter: NoteAdapter
    private lateinit var searchBar: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_notes, container, false)
        val rv = v.findViewById<RecyclerView>(R.id.notesRecycler)
        rv.layoutManager = LinearLayoutManager(requireContext())
        searchBar = v.findViewById(R.id.searchBar)

        adapter = NoteAdapter(
            NoteRepository.getAll().toMutableList(),
            onEdit = { note -> showEditDialog(note) },
            onDelete = { note -> confirmDelete(note) }
        )
        rv.adapter = adapter

        v.findViewById<View>(R.id.fabAddNote).setOnClickListener { showAddDialog() }

        setupSearch()
        return v
    }

    private fun setupSearch() {
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterNotes(s.toString())
            }
        })
    }

    private fun filterNotes(query: String) {
        val allNotes = NoteRepository.getAll()
        val filtered = if (query.isEmpty()) allNotes
        else allNotes.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.content.contains(query, ignoreCase = true)
        }
        adapter.updateData(filtered)
    }

    private fun showAddDialog() {
        val ctx = requireContext()
        val editTitle = EditText(ctx).apply { hint = "Title" }
        val editContent = EditText(ctx).apply { hint = "Content" }
        val layout = LinearLayoutCompat(ctx).apply {
            orientation = LinearLayoutCompat.VERTICAL
            addView(editTitle)
            addView(editContent)
        }

        AlertDialog.Builder(ctx)
            .setTitle("Add Note")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                val note = Note(
                    id = System.currentTimeMillis(),
                    title = editTitle.text.toString(),
                    content = editContent.text.toString(),
                    timestamp = System.currentTimeMillis(),
                    createdAt = System.currentTimeMillis()
                )
                NoteRepository.add(note)
                refresh()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditDialog(note: Note) {
        val ctx = requireContext()
        val editTitle = EditText(ctx).apply { setText(note.title) }
        val editContent = EditText(ctx).apply { setText(note.content) }
        val layout = LinearLayoutCompat(ctx).apply {
            orientation = LinearLayoutCompat.VERTICAL
            addView(editTitle)
            addView(editContent)
        }

        AlertDialog.Builder(ctx)
            .setTitle("Edit Note")
            .setView(layout)
            .setPositiveButton("Save") { _, _ ->
                val updated = note.copy(
                    title = editTitle.text.toString(),
                    content = editContent.text.toString(),
                    timestamp = System.currentTimeMillis()
                )
                NoteRepository.update(updated)
                refresh()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun confirmDelete(note: Note) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Note?")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Delete") { _, _ ->
                NoteRepository.delete(note)
                refresh()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun refresh() {
        adapter.updateData(NoteRepository.getAll())
    }
}
