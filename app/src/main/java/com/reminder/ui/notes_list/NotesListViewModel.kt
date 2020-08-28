package com.reminder.ui.notes_list

import androidx.lifecycle.LiveData
import com.reminder.utils.AppConstants
import com.reminder.ui.base.BaseViewModel
import com.reminder.data.IListItem
import com.reminder.data.Note

class NotesListViewModel : BaseViewModel() {

    fun fetchAllNotes():LiveData<List<IListItem>>{
        return appRepository.getNotesList()
    }

    fun updateNote(note: Note) {
        var result = appRepository.updateNote(note)
        if(result == AppConstants.OPERATION_FAILED){
            toastMessage.value = "Note Completion failed"
        }
    }

    fun removeNote(note: Note) {
        var result = appRepository.delete(note)
        if(result.toLong() == AppConstants.OPERATION_FAILED){
            toastMessage.value = "Deleting notes failed"
        }
    }

}