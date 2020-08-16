package com.employeeapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reminder.data.Note

@Dao
interface AppDao {

    @Query("SELECT * FROM note")
    fun getAllNotes():LiveData<List<Note>>

    @Update
    fun updateNotes(notes: Note):Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertNotes(notes: Note):Long

    @Delete
    fun deleteNotes(notes: Note):Int

    @Query("SELECT * FROM note WHERE notes_id=:notesId")
    fun getNotesInfo(notesId: String):LiveData<Note>
}