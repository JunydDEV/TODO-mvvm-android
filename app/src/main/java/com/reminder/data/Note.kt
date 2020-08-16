package com.reminder.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note(
    @PrimaryKey @ColumnInfo(name = "notes_id") var notesId: String,
    @ColumnInfo(name = "notes_title") var notesTitle: String,
    @ColumnInfo(name = "notes_description") var notesDescription: String,
    @ColumnInfo(name = "notes_date") var notesDate: String
):IListItem