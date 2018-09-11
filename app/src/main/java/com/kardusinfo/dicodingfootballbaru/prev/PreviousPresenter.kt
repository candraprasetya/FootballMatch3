package com.kardusinfo.dicodingfootballbaru.prev

import com.google.gson.Gson
import com.kardusinfo.dicodingfootballbaru.CoroutineContextProvider
import com.kardusinfo.dicodingfootballbaru.api.ApiRepository
import com.kardusinfo.dicodingfootballbaru.api.MainApi
import com.kardusinfo.dicodingfootballbaru.model.PreviousResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PreviousPresenter(private val view: PreviousView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMatchList(league: String?) {


        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(MainApi.eventsPastLeague(league)),
                        PreviousResponse::class.java)
            }
            view.showMatchList(data.await().events)
        }
    }
}