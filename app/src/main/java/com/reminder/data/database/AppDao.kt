package com.employeeapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reminder.data.Note

@Dao
interface AppDao {

    @Query("SELECT * FROM note")
    fun getAllNotes():LiveData<List<Note>>

    @Update
    fun updateEmployee(employee: Note):Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertNotes(employee: Note):Long

    @Delete
    fun deleteEmployee(employee: Note):Int

    @Query("SELECT * FROM note WHERE notes_id=:notesId")
    fun getEmployeeInfo(notesId: String):LiveData<Note>
}