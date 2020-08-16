package com.reminder.ui.notes_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.employeeapp.ui.base.BaseFragment
import com.reminder.R
import kotlinx.android.synthetic.main.task_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class NotesListFragment : BaseFragment(R.layout.task_list_fragment) {

    private val viewModel:NotesListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseViewModel = viewModel
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAllNotes().observe(viewLifecycleOwner, Observer {
            recyclerViewReminders.layoutManager = LinearLayoutManager(context)
            recyclerViewReminders.adapter = NotesAdapter(it)

        })

        fab.setOnClickListener {
            navigate(requireView(),R.id.action_notesListFragment_to_createNoteFragment)
        }

    }


}