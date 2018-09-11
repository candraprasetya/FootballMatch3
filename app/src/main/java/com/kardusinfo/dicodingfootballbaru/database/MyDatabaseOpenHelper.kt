package com.kardusinfo.dicodingfootballbaru.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = com.kardusinfo.dicodingfootballbaru.database.MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(myDB.TABLE_FAVORITE, true,
                myDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                myDB.EVENT_ID to TEXT,
                myDB.EVENT_DTE to TEXT,
                myDB.TEAM_HOME_NAME to TEXT,
                myDB.TEAM_AWAY_NAME to TEXT,
                myDB.EVENT_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(myDB.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)