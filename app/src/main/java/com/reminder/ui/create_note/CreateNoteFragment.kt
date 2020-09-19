package com.reminder.ui.create_note

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import com.employeeapp.ui.base.BaseFragment
import com.reminder.R
import com.reminder.data.Note
import com.reminder.di.DaggerAppComponents
import com.reminder.utils.AppConstants
import kotlinx.android.synthetic.main.create_note_fragment.*
import java.util.*
import javax.inject.Inject


class CreateNoteFragment : BaseFragment(R.layout.create_note_fragment),
    DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var viewModel: CreateNoteViewModel

    private var date: String = ""
    private var updateInfo = false
    private var note: Note? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        DaggerAppComponents.create().inject(this)

        baseViewModel = viewModel
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            var notesId = it.getString(AppConstants.KEY_NOTE_ID)
            viewModel.getNotesInfo(notesId!!)
                .observe(viewLifecycleOwner, androidx.lifecycle.Observer { notes ->
                    note = notes
                    editTextNotesTitle.setText(notes.notesTitle)
                    editTextNotes.setText(notes.notesDescription)
                textViewChooseDate.text =  notes.notesDate
                date = notes.notesDate
                updateInfo = true
            })
        }

        buttonSaveNote.setOnClickListener {


            if(!updateInfo) {
                var notes = Note(
                    UUID.randomUUID().toString(),
                    editTextNotesTitle.text.toString(),
                    editTextNotes.text.toString(),
                    date,
                    false
                )

                viewModel.insertNotes(notes)
                    .observe(viewLifecycleOwner, androidx.lifecycle.Observer { isInserted ->
                        if (isInserted) {
                            finish()
                        } })
            }else{
                var notes = Note(
                    note?.notesId!!,
                    editTextNotesTitle.text.toString(),
                    editTextNotes.text.toString(),
                    date,
                    note?.isCompleted!!
                )
                viewModel.updateNote(notes)
                finish()
            }
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