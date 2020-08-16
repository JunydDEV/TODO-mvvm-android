package com.reminder.ui.notes_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reminder.R
import com.reminder.ui.base.BaseAdapter
import com.reminder.data.IListItem
import com.reminder.data.Note
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_reminder.view.*

class NotesAdapter(var notesList:List<IListItem>): BaseAdapter() {

    override fun getViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_reminder,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItems(): List<IListItem> {
        return notesList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var notes = notesList[position] as Note
        var notesViewHolder = holder as NotesViewHolder

        notesViewHolder.setNotes(notes)
    }

    class NotesViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun setNotes(note:Note){
            containerView.textViewTaskTitle.text = note.notesTitle
            containerView.textViewTaskDescription.text = note.notesDescription
            containerView.textViewTaskDate.text = note.notesDate
        }
    }


}