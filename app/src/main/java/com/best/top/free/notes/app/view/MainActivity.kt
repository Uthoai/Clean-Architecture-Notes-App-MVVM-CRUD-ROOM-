package com.best.top.free.notes.app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.best.top.free.notes.app.R
import com.best.top.free.notes.app.adapter.NoteAdapter
import com.best.top.free.notes.app.database.NoteDatabase
import com.best.top.free.notes.app.databinding.ActivityMainBinding
import com.best.top.free.notes.app.models.Note
import com.best.top.free.notes.app.models.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: NoteDatabase
    lateinit var viewModel: NoteViewModel
    lateinit var adapter: NoteAdapter
    lateinit var selectedNote: Note
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}