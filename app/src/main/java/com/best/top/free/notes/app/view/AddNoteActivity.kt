package com.best.top.free.notes.app.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.best.top.free.notes.app.R
import com.best.top.free.notes.app.databinding.ActivityAddNoteBinding
import com.best.top.free.notes.app.models.Note
import com.best.top.free.notes.app.utilities.CURRENT_NOTE
import java.text.SimpleDateFormat

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var note: Note
    private lateinit var oldNote: Note
    var isUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldNote = intent.getSerializableExtra(CURRENT_NOTE) as Note
            binding.apply {
                etTitle.setText(oldNote.title)
                etDetail.setText(oldNote.content)
            }
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val detail = binding.etDetail.text.toString()

            if (title.isNotEmpty() || detail.isNotEmpty()) {

                val timeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")

                if (isUpdate) {
                    note = Note(
                        oldNote.id,
                        title,
                        detail,
                        timeFormat.format(System.currentTimeMillis())
                    )
                }else{
                    note = Note(
                        null,
                        title,
                        detail,
                        timeFormat.format(System.currentTimeMillis())
                    )
                }

                val intent = Intent()
                intent.putExtra(CURRENT_NOTE, note)
                setResult(RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this, "Please enter some data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}