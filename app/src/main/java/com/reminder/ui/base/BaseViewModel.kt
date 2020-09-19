package com.reminder.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.employeeapp.data.AppRepository
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var appRepository: AppRepository

    val toastMessage = MutableLiveData<String>()
}