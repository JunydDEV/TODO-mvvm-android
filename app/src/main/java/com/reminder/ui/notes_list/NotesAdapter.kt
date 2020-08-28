package com.reminder.ui.notes_list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.reminder.R
import com.reminder.data.IListItem
import com.reminder.data.Note
import com.reminder.ui.base.BaseAdapter
import com.reminder.utils.AppConstants
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_reminder.view.*

class NotesAdapter(var notesList: List<IListItem>) : BaseAdapter() {

    var onCheckboxCheckListener: OnCheckboxCheckListener? = null
    var onItemClickListener: OnItemClickListener? = null
    var onCreateNewTaskListener: OnCreateNewTaskListener? = null
    var onItemLongPressListener: OnItemLongPressListener? = null

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)

        return if (viewType == AppConstants.TYPE_ITEM_TASK) {
            var view = inflater.inflate(R.layout.item_reminder, parent, false)
            NotesViewHolder(view)
        } else {
            var view = inflater.inflate(R.layout.item_empty_list, parent, false)
            EmptyListViewHolder(view)
        }
    }

    override fun getItems(): List<IListItem> {
        return notesList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NotesViewHolder) {
            var notes = notesList[position] as Note
            holder.setNotes(notes)
            holder.containerView.checkboxCompleteTask.setOnCheckedChangeListener { buttonView, isChecked ->
                onCheckboxCheckListener?.onCheck(isChecked, notes)
            }
            holder.containerView.setOnClickListener {
                onItemClickListener?.onClick(position)
            }
            holder.containerView.setOnLongClickListener {
                onItemLongPressListener?.onPressed(position)
                true
            }
        } else {
            var emptyViewHolder = holder as EmptyListViewHolder
            emptyViewHolder.containerView.setOnClickListener {
                onCreateNewTaskListener?.onCreate()
            }
        }
    }

    class NotesViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun setNotes(note: Note) {
            containerView.textViewTaskTitle.text = note.notesTitle

            if (note.isCompleted) {
                containerView.checkboxCompleteTask.isChecked = true
                containerView.textViewTaskTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                containerView.checkboxCompleteTask.isChecked = false
                containerView.textViewTaskTitle.paintFlags = Paint.ANTI_ALIAS_FLAG
            }

            containerView.textViewTaskDescription.text = note.notesDescription
            containerView.textViewTaskDate.text = note.notesDate
        }
    }

    class EmptyListViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    }

    interface OnCheckboxCheckListener {
        fun onCheck(boolean: Boolean, note: Note)
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    interface OnCreateNewTaskListener {
        fun onCreate()
    }

    interface OnItemLongPressListener {
        fun onPressed(position: Int)
    }

}