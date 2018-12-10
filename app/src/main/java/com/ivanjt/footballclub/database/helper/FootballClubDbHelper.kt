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
                instance = FootballClubDbHelper(context)
            }

            return instance!!
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.createTable(
            "favourite_match", true,
            "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "event_id" to TEXT + UNIQUE,
            "home_team_name" to TEXT,
            "home_team_badge" to TEXT,
            "home_score" to TEXT,
            "away_team_name" to TEXT,
            "away_team_badge" to TEXT,
            "away_score" to TEXT,
            "date" to TEXT,
            "time" to TEXT
        )

        p0?.createTable(
            "favourite_team", true,
            "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "team_id" to TEXT + UNIQUE,
            "name" to TEXT,
            "badge" to TEXT,
            "year" to TEXT,
            "stadium" to TEXT,
            "description" to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable("favourite_match", true)
        p0?.dropTable("favourite_team", true)
    }

}