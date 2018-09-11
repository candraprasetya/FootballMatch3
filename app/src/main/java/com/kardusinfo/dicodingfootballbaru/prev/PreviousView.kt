package com.kardusinfo.dicodingfootballbaru.prev

import com.kardusinfo.dicodingfootballbaru.model.Events

interface PreviousView {
    fun showMatchList(data: List<Events>)
}