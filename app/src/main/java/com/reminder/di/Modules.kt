package com.reminder.di

import android.app.Application
import com.employeeapp.data.AppRepository
import com.reminder.ui.create_note.CreateNoteFragment
import com.reminder.ui.create_note.CreateNoteViewModel
import com.reminder.ui.notes_list.NotesListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {

    single {
        AppRepository(androidApplication())
    }
    viewModel {
        NotesListViewModel()
    }
    viewModel {
        CreateNoteViewModel()
    }
}