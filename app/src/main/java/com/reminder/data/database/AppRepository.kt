package com.employeeapp.data

import androidx.lifecycle.LiveData
import com.reminder.MyApplication
import com.reminder.data.IListItem
import com.reminder.data.Note
import com.reminder.utils.AppConstants
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AppRepository @Inject constructor(application: MyApplication) {

    private var appDatabase = AppDatabase.getDatabase(application)
    lateinit var dispatch: CoroutineDispatcher

    fun insertNotes(note: Note): Long {
        return try {
            appDatabase.notesDao().insertNotes(note)
        } catch (e: Exception) {
            AppConstants.OPERATION_FAILED
        }
    }

    fun getNotesList(): LiveData<List<IListItem>> {
        return appDatabase.notesDao().getAllNotes() as LiveData<List<IListItem>>
    }

    fun removeEmployee(employee: Note): Long {
        return appDatabase.notesDao().deleteNotes(employee).toLong()
    }

    fun getNotesInfo(notesInfo: String): LiveData<Note> {
        return appDatabase.notesDao().getNotesInfo(notesInfo)
    }

    fun updateNote(note: Note): Long? {
        return appDatabase.notesDao().updateNotes(note).toLong()
    }

    fun delete(note: Note): Int? {
        return appDatabase.notesDao().deleteNotes(note)
    }
}