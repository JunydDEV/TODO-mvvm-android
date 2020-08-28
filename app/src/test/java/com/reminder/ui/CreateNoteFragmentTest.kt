package com.reminder.ui

import com.employeeapp.data.AppRepository
import com.reminder.MyApplication
import com.reminder.data.Note
import com.reminder.utils.AppConstants
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CreateNoteFragmentTest{

    @Test
    fun createNote(){
        var id = UUID.randomUUID().toString()
        var note = Note(id,"Test Title","Notes Description", "21/09/2019",false)
        var appRepository = AppRepository(MyApplication())

        assertEquals(true,validateNotes(note))

        var result = appRepository.insertNotes(note)

        assertEquals(AppConstants.OPERATION_FAILED,result)
    }

    private fun validateNotes(notes: Note):Boolean{
        when{
            notes.notesTitle.isNullOrEmpty()->{
                return false
            }
            notes.notesDescription.isNullOrEmpty()->{
                return false
            }
            notes.notesDate.isNullOrEmpty()->{
                return false
            }
        }
        return true
    }
}