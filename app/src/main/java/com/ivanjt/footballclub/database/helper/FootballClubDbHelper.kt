package com.ivanjt.footballclub.database.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FootballClubDbHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "FootballClub.db", null, 1) {

    companion object {
        private var instance: FootballClubDbHelper? = null

        @Synchronized
        fun getInstance(context: Context): FootballClubDbHelper {
            if (instance == null) {
                instance =
                        FootballClubDbHelper(context)
            }

            return instance!!
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.createTable(
            "favourites", true,
            "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "event_id" to TEXT + UNIQUE,
            "home_team_name" to TEXT,
            "home_team_badge" to TEXT,
            "home_score" to TEXT,
            "away_team_name" to TEXT,
            "away_team_badge" to TEXT,
            "away_score" to TEXT,
            "date" to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable("favourites", true)
    }

}