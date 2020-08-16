package com.employeeapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reminder.data.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): AppDao

}