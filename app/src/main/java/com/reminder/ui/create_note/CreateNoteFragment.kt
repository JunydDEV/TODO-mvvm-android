package com.reminder.ui.create_note

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.employeeapp.ui.base.BaseFragment
import com.reminder.R
import com.reminder.data.Note
import kotlinx.android.synthetic.main.create_note_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class CreateNoteFragment : BaseFragment(R.layout.create_note_fragment),
    DatePickerDialog.OnDateSetListener {
    private val viewModel: CreateNoteViewModel by viewModel()
    private var date:String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseViewModel = viewModel
        super.onViewCreated(view, savedInstanceState)

        buttonSaveNote.setOnClickListener {
            var notes = Note(
                UUID.randomUUID().toString(),
                editTextNotesTitle.text.toString(),
                editTextNotes.text.toString(),
                date
            )
            viewModel.insertNotes(notes).observe(viewLifecycleOwner, androidx.lifecycle.Observer {isInserted->
                if(isInserted){
                    finish()
                }
            })
        }

        textViewChooseDate.setOnClickListener {
            var calendar = Calendar.getInstance()

            var dialog = DatePickerDialog(requireContext(), this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))

            dialog.show()
        }    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = "${dayOfMonth}-${month}-${year}"
        textViewChooseDate.text = date
    }


}