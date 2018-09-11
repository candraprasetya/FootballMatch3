package com.kardusinfo.dicodingfootballbaru.next

import com.kardusinfo.dicodingfootballbaru.model.Events

interface NextView {
    fun showMatchList(data: List<Events>)
}