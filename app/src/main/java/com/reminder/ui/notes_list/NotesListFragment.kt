package com.reminder.ui.notes_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.employeeapp.ui.base.BaseFragment
import com.employeeapp.utils.AppConstants
import com.reminder.R
import com.reminder.data.IListItem
import com.reminder.data.Note
import kotlinx.android.synthetic.main.task_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class NotesListFragment : BaseFragment(R.layout.task_list_fragment) {

    private val viewModel:NotesListViewModel by viewModel()
    private var adapter:NotesAdapter? = null
    private var notesList = mutableListOf<IListItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseViewModel = viewModel
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAllNotes().observe(viewLifecycleOwner, Observer {
            notesList.clear()
            notesList.addAll(it)
            adapter?.notifyDataSetChanged()
        })

        setNotesList()

        fab.setOnClickListener {
            navigate(requireView(),R.id.action_notesListFragment_to_createNoteFragment)
        }

    }

    private fun setNotesList() {
        adapter = NotesAdapter(notesList)
        recyclerViewReminders.layoutManager = LinearLayoutManager(context)
        recyclerViewReminders.adapter = adapter

        adapter?.onCheckboxCheckListener = object : NotesAdapter.OnCheckboxCheckListener {
            override fun onCheck(isChecked: Boolean, note: Note) {
                note.isCompleted = isChecked

                viewModel.updateNote(note)
                adapter?.notifyDataSetChanged()
            }
        }

        adapter?.onItemClickListener = object: NotesAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                var bundle = Bundle()
                bundle.putString(AppConstants.KEY_NOTE_ID, notesList[position].getId())
                navigate(requireView(),R.id.action_notesListFragment_to_createNoteFragment,bundle)
            }
        }
    }


}