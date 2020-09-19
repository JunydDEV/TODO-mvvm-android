package com.reminder

import android.app.Application
import com.employeeapp.data.AppDatabase
import javax.inject.Inject

class MyApplication @Inject constructor() : Application() {

    override fun onCreate() {
        super.onCreate()

        AppDatabase.getDatabase(this)
    }
}