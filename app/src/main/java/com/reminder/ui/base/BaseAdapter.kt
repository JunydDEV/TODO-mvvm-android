package com.reminder.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reminder.data.IListItem

abstract class BaseAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    abstract fun getViewHolder(parent: ViewGroup,viewType: Int):RecyclerView.ViewHolder
    abstract fun getItems():List<IListItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return getViewHolder(parent,viewType)
    }

    override fun getItemCount(): Int {
        return getItems().size
    }

    override fun getItemViewType(position: Int): Int {
        return getItems()[position].getType()
    }
}