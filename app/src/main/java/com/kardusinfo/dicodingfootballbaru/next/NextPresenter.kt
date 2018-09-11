package com.kardusinfo.dicodingfootballbaru.next

import com.google.gson.Gson
import com.kardusinfo.dicodingfootballbaru.api.ApiRepository
import com.kardusinfo.dicodingfootballbaru.api.MainApi
import com.kardusinfo.dicodingfootballbaru.model.NextResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextPresenter(private val view: NextView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getMatchList() {
        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(MainApi.eventsNextMatch("4328")),
                        NextResponse::class.java
                )
            }
            view.showMatchList(data.await().events)
        }
    }
}