package com.reminder.data

import com.reminder.utils.AppConstants

class Empty:IListItem {
    override fun getId(): String {
        return ""
    }

    override fun getType(): Int {
        return AppConstants.TYPE_ITEM_EMPTY
    }
}