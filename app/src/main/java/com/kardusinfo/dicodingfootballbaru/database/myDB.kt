package com.kardusinfo.dicodingfootballbaru.database

data class myDB(val id: Long?, val event_id: String?, val event_dte: String?,
                    val team_home_name: String?, val team_away_name: String?, val event_score: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DTE: String = "EVENT_DTE"
        const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
        const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
        const val EVENT_SCORE: String = "EVENT_SCORE"
    }
}