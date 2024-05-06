package com.best.top.free.notes.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.best.top.free.notes.app.R
import com.best.top.free.notes.app.databinding.ListItemBinding
import com.best.top.free.notes.app.models.Note
import kotlin.random.Random

class NoteAdapter(private val context: Context,private val listener: NotesItemClickListener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val noteList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        noteList[position].let {note->
            holder.binding.apply {
                tvTitle.text = note.title
                tvTitle.isSelected = true

                tvContent.text = note.content

                tvDate.text = note.date
                tvDate.isSelected = true

                itemCardLayout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))


                root.setOnClickListener {
                    listener.onItemClicked(note)
                }
                root.setOnLongClickListener {
                    listener.onLongItemClicked(note,itemCardLayout)
                    true
                }
            }
        }
    }

    private fun randomColor(): Int {
        val list = arrayListOf(
            R.color.yellow,
            R.color.blue,
            R.color.skyBlue,
            R.color.lavender,
            R.color.orange,
            R.color.pink,
            R.color.green,
            R.color.Cyan,
            R.color.itemBg
        )
        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    fun update(newList: List<Note>){
        fullList.clear()
        fullList.addAll(newList)
        noteList.clear()
        noteList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search: String){
        noteList.clear()
        for (item in fullList) {
            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.content?.lowercase()?.contains(search.lowercase()) == true
            ) {
                noteList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface NotesItemClickListener {
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }

}