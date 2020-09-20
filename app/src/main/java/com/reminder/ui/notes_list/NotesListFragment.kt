package com.reminder.ui.notes_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.employeeapp.ui.base.BaseFragment
import com.reminder.R
import com.reminder.data.Empty
import com.reminder.data.IListItem
import com.reminder.data.Note
import com.reminder.di.DaggerAppComponents
import com.reminder.utils.AppConstants
import kotlinx.android.synthetic.main.item_delete.*
import kotlinx.android.synthetic.main.task_list_fragment.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesListFragment : BaseFragment(R.layout.task_list_fragment) {

    @Inject
    lateinit var viewModel: NotesListViewModel

    private var adapter: NotesAdapter? = null
    private var notesList = mutableListOf<IListItem>()
    private var note: Note? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerAppComponents.create().inject(this)

        baseViewModel = viewModel
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchAllNotes().observe(viewLifecycleOwner, Observer {
            notesList.clear()
            if (it.isNotEmpty()) {
                notesList.addAll(it)
                fab.visibility = View.VISIBLE
            } else {
                notesList.add(Empty())
                fab.visibility = View.GONE
            }

            MainScope().launch {
                adapter?.notifyDataSetChanged()
            }
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

        adapter?.onCreateNewTaskListener = object: NotesAdapter.OnCreateNewTaskListener{
            override fun onCreate() {
                navigate(requireView(),R.id.action_notesListFragment_to_createNoteFragment)
            }
        }

        adapter?.onItemLongPressListener = object: NotesAdapter.OnItemLongPressListener{
            override fun onPressed(position: Int) {
                note = notesList[position] as Note
                layoutDeleteItem.visibility = View.VISIBLE
                fab.visibility = View.GONE
            }
        }

        imageViewDelete.setOnClickListener {
            viewModel.removeNote(note!!)
            notesList.remove(note!!)
            layoutDeleteItem.visibility = View.GONE

            if(notesList.size>0){
                fab.visibility = View.VISIBLE
            }else{
                notesList.add(Empty())
                adapter?.notifyDataSetChanged()
            }
        }
    }


}