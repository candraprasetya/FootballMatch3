package com.kardusinfo.dicodingfootballbaru.info

import com.kardusinfo.dicodingfootballbaru.model.Events

interface InfoView {
    fun showMainData(data: List<Events>)
}