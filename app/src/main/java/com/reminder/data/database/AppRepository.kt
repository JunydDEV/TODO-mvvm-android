package com.employeeapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.reminder.data.IListItem
import com.reminder.data.Note
import java.lang.Exception

class AppRepository(var application: Application) {

    private var appDatabase: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "notes-db"
    ).allowMainThreadQueries().build()

    fun insertNotes(note: Note): Long {
        return try {
            appDatabase.notesDao().insertNotes(note)
        }catch (e:Exception){
            -1
        }
    }

    fun getNotesList(): LiveData<List<IListItem>> {
        return appDatabase.notesDao().getAllNotes() as LiveData<List<IListItem>>
    }

    fun removeEmployee(employee: Note): Long {
        return appDatabase.notesDao().deleteEmployee(employee).toLong()
    }

    fun getEmployeeInfo(userName: String): LiveData<Note> {
        return appDatabase.notesDao().getEmployeeInfo(userName)
    }

    fun getTypesList(): MutableList<String> {
        var typesList = mutableListOf<String>()

        typesList.add("--No Selected--")
        typesList.add("Accounting")
        typesList.add("Sales")
        typesList.add("Plant")
        typesList.add("Shipping")
        typesList.add("Quality Control")

        return typesList
    }

    fun getRolesList(): MutableList<String> {
        var rolesList = mutableListOf<String>()

        rolesList.add("ADMIN")
        rolesList.add("ACCT_PAY")
        rolesList.add("ACCT_RCV")
        rolesList.add("EMP_BENEFITS")
        rolesList.add("GEN_LEDGER")
        rolesList.add("PAYROLL")
        rolesList.add("INVENTORY")
        rolesList.add("PRODUCTION")
        rolesList.add("QUALITY_CTR")
        rolesList.add("SALES")
        rolesList.add("ORDERS")
        rolesList.add("CUSTOMERS")
        rolesList.add("SHIPPING")
        rolesList.add("RETURN")

        return rolesList
    }

    fun updateEmployee(employee: Note): Long {
        return appDatabase.notesDao().updateEmployee(employee).toLong()
    }
}