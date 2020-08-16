package com.reminder.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reminder.data.IListItem

abstract class BaseAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    abstract fun getViewHolder(parent: ViewGroup):RecyclerView.ViewHolder
    abstract fun getItems():List<IListItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return getViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return getItems().size
    }
}