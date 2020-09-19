package com.employeeapp.data

import androidx.lifecycle.LiveData
import com.reminder.MyApplication
import com.reminder.data.IListItem
import com.reminder.data.Note
import com.reminder.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor(application: MyApplication) {

    private var appDatabase = AppDatabase.getDatabase(application)

    suspend fun insertNotes(note: Note): Long {
        return try {
            withContext(Dispatchers.IO) {
                appDatabase.notesDao().insertNotes(note)
            }
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

    suspend fun updateNote(note: Note): Long? {
        var result: Long?
        withContext(Dispatchers.IO) {
            result = appDatabase.notesDao().updateNotes(note).toLong()
        }
        return result
    }

    suspend fun delete(note: Note): Int? {
        var result: Int?
        withContext(Dispatchers.IO) {
            result = appDatabase.notesDao().deleteNotes(note)
        }

        return result
    }
}