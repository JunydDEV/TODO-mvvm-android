package com.reminder.ui.notes_list

import androidx.lifecycle.LiveData
import com.reminder.ui.base.BaseViewModel
import com.reminder.data.IListItem

class NotesListViewModel : BaseViewModel() {

    fun fetchAllNotes():LiveData<List<IListItem>>{
        return appRepository.getNotesList()
    }

}