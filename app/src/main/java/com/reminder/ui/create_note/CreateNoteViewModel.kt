package com.reminder.ui.create_note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reminder.ui.base.BaseViewModel
import com.reminder.utils.AppConstants
import com.reminder.data.Note

class CreateNoteViewModel : BaseViewModel() {

    private fun validateNotes(notes: Note):Boolean{
        when{
            notes.notesTitle.isNullOrEmpty()->{
                toastMessage.value = "Please enter notes title"
                return false
            }
            notes.notesDescription.isNullOrEmpty()->{
                toastMessage.value = "Please enter notes description"
                return false
            }
            notes.notesDate.isNullOrEmpty()->{
                toastMessage.value = "Please select date"
                return false
            }
        }
        return true
    }

    fun insertNotes(notes: Note):LiveData<Boolean>{
        var resultLiveData = MutableLiveData<Boolean>()
        if(validateNotes(notes)){
            var result = appRepository.insertNotes(notes)
            if(result == AppConstants.OPERATION_FAILED){
                toastMessage.value = "Notes creation failed."
                resultLiveData.value = false
            }else{
                toastMessage.value = "Notes created successfully."
                resultLiveData.value = true
            }
        }

        return resultLiveData
    }

    fun getNotesInfo(notesId: String):LiveData<Note> {
        return appRepository.getNotesInfo(notesId)
    }

    fun updateNote(note: Note) {
        var result = appRepository.updateNote(note)
        if(result == AppConstants.OPERATION_FAILED){
            toastMessage.value = "Note Completion failed"
        }
    }
}