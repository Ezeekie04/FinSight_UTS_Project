package com.example.finsight.repo

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.edit
import com.example.finsight.model.Note
import org.json.JSONArray
import org.json.JSONObject

@SuppressLint("StaticFieldLeak")
object NoteRepository {

    private const val PREF = "finsight_prefs"
    private const val KEY = "notes_json"
    private lateinit var prefsContext: Context
    private val notes = mutableListOf<Note>()

    fun init(context: Context) {
        prefsContext = context.applicationContext
        load()
    }

    private fun load() {
        val prefs = prefsContext.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY, null) ?: return
        try {
            val arr = JSONArray(json)
            notes.clear()
            for (i in 0 until arr.length()) {
                val o = arr.getJSONObject(i)
                val n = Note(
                    id = o.getLong("id"),
                    title = o.getString("title"),
                    content = o.getString("content"),
                    timestamp = o.optLong("timestamp", System.currentTimeMillis()),
                    createdAt = o.optLong("createdAt", System.currentTimeMillis())
                )
                notes.add(n)
            }
        } catch (t: Throwable) {
            notes.clear()
        }
    }

    private fun save() {
        val arr = JSONArray()
        for (n in notes) {
            val o = JSONObject()
            o.put("id", n.id)
            o.put("title", n.title)
            o.put("content", n.content)
            o.put("timestamp", n.timestamp)
            o.put("createdAt", n.createdAt)
            arr.put(o)
        }
        prefsContext.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit { putString(KEY, arr.toString()) }
    }

    fun getAll(): List<Note> = notes.toList()

    fun add(note: Note) {
        notes.add(0, note)
        save()
    }

    fun update(note: Note) {
        val idx = notes.indexOfFirst { it.id == note.id }
        if (idx >= 0) {
            notes[idx] = note
            save()
        }
    }

    fun delete(note: Note) {
        notes.removeAll { it.id == note.id }
        save()
    }
}
