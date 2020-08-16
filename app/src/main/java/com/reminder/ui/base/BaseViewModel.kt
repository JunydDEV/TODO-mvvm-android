package com.reminder.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.employeeapp.data.AppRepository
import org.koin.core.KoinComponent
import org.koin.core.get

open class BaseViewModel : ViewModel(), KoinComponent {
    var appRepository = get<AppRepository>()
    val toastMessage = MutableLiveData<String>()
}