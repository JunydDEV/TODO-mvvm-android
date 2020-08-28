package com.reminder.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reminder.utils.AppConstants

@Entity
class Note(
    @PrimaryKey @ColumnInfo(name = "notes_id") var notesId: String,
    @ColumnInfo(name = "notes_title") var notesTitle: String,
    @ColumnInfo(name = "notes_description") var notesDescription: String,
    @ColumnInfo(name = "notes_date") var notesDate: String,
    @ColumnInfo(name = "is_completed") var isCompleted: Boolean
):IListItem {
    override fun getId(): String {
        return notesId
    }

    override fun getType(): Int {
        return AppConstants.TYPE_ITEM_TASK
    }
}