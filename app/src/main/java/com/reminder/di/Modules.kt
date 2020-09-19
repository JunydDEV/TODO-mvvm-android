package com.reminder.di

import com.reminder.ui.create_note.CreateNoteViewModel
import com.reminder.ui.notes_list.NotesListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {

    single {
        //AppRepository(androidApplication())
    }
    viewModel {
        NotesListViewModel()
    }
    viewModel {
        CreateNoteViewModel()
    }
}