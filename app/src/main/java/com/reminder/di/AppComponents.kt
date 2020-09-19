package com.reminder.di

import com.reminder.ui.create_note.CreateNoteFragment
import com.reminder.ui.notes_list.NotesListFragment
import dagger.Component

@Component
interface AppComponents {

    fun inject(createNoteFragment: CreateNoteFragment)

    fun inject(notesListFragment: NotesListFragment)

}