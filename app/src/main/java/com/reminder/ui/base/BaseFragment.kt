package com.employeeapp.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.reminder.ui.base.BaseViewModel

open class BaseFragment(layout: Int) : Fragment(layout) {

    lateinit var baseViewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseViewModel.toastMessage.observe(viewLifecycleOwner, Observer { message ->
            context?.let {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun navigate(view: View?, navDirections: NavDirections) {
        view?.let {
            Navigation.findNavController(view).navigate(navDirections)
        }
    }

    fun navigate(view: View?, @IdRes id: Int) {
        view?.let {
            Navigation.findNavController(it).navigate(id)
        }
    }

    fun navigate(view: View?, @IdRes id: Int, bundle: Bundle) {
        view?.let {
            Navigation.findNavController(it).navigate(id, bundle)
        }
    }

    fun finish(){
        findNavController().popBackStack()
    }

    fun getStringById(id: Int): String {
        return resources.getString(id)
    }

}