package com.example.gezirehberim.DAL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(
    context: Context,
    dbName: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) :
    SQLiteOpenHelper(context, dbName, factory, version) {
    override fun onCreate(p0: SQLiteDatabase) {
        var query =
            "CREATE TABLE Place(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name TEXT,LocationDefinition TEXT,Description TEXT,Priority INTEGER,Latitude REAL NOT NULL,Longitude REAL NOT NULL,IsVisited INTEGER NOT NULL) "
        p0.execSQL(query)
        query =
            "CREATE TABLE Visitation(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,PlaceId INTEGER NOT NULL,Date TEXT,Amount REAL NOT NULL, FOREIGN KEY(PlaceId) REFERENCES Place(Id)) "
        p0.execSQL(query)
        query =
            "CREATE TABLE Picture(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,PlaceId INTEGER ,VisitationId INTEGER,Data TEXT NOT NULL) "
        p0.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}