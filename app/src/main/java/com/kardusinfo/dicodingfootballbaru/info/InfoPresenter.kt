package com.kardusinfo.dicodingfootballbaru.info

import com.google.gson.Gson
import com.kardusinfo.dicodingfootballbaru.api.ApiRepository
import com.kardusinfo.dicodingfootballbaru.api.MainApi
import com.kardusinfo.dicodingfootballbaru.model.PreviousResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class InfoPresenter(private val view: InfoView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun loadMainData(event_id: String) {
        async(UI) {
            val data = bg {
                Gson().fromJson(apiRepository.doRequest(MainApi.lookupEvent(event_id)),
                        PreviousResponse::class.java
                )
            }
            view.showMainData(data.await().events)
        }
    }
}