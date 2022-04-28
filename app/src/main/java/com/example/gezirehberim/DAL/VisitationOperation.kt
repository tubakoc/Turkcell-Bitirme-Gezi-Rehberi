package com.example.gezirehberim.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.gezirehberim.model.Visitation

class VisitationOperation(context: Context) {
    var visitationDatabase: SQLiteDatabase? = null
    private val DATE = "Date"
    private val DESCRIPTION = "Description"
    private val TABLENAME = "Visitation"
    private val PLACEID="PlaceId"

    var dbOpenHelper: DatabaseOpenHelper

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "TripApp", null, 1)
    }

    private fun open() {

        visitationDatabase = dbOpenHelper.writableDatabase
    }

    private fun close() {
        if (visitationDatabase != null && visitationDatabase!!.isOpen) {
            visitationDatabase!!.close()
        }
    }

    fun addVisitation(visitation: Visitation): Long {
        val contentValues = ContentValues()
        contentValues.put(DATE, visitation.date)
        contentValues.put(PLACEID,visitation.placeId)
        contentValues.put(DESCRIPTION, visitation.description)
        open()
        val record = visitationDatabase!!.insert(TABLENAME, null, contentValues)
        close()
        return record

    }

    fun updateVisitation(visitation: Visitation) {

        val contentValues = ContentValues()
        contentValues.put(DATE, visitation.date)
        contentValues.put(DESCRIPTION, visitation.description)

        open()
        visitationDatabase!!.update(
            TABLENAME,
            contentValues,
            "Id = ?",
            arrayOf(visitation.id.toString())
        )
        close()


    }

    fun deleteVisitation(id: Int) {

        open()
        visitationDatabase!!.delete(TABLENAME, "Id = ?", arrayOf(id.toString()))
        close()
    }

    private fun getAllVisitations(): Cursor {
        val query = "Select * from $TABLENAME"
        return visitationDatabase!!.rawQuery(query, null)
    }

    private fun getVisitationByPlaceId(placeId: Int): Cursor {
        val query = "Select * from $TABLENAME where PlaceId=?"
        return visitationDatabase!!.rawQuery(query, arrayOf(placeId.toString()))
    }


    @SuppressLint("Range")
    fun getVisitation(placeId: Int?): ArrayList<Visitation> {
        open()
        val visitationList = ArrayList<Visitation>()
        var visitation: Visitation
        val cursor: Cursor = if (placeId != null) {
            getVisitationByPlaceId(placeId)
        } else {
            getAllVisitations()
        }


        if (cursor.moveToFirst()) {
            do {
                visitation = Visitation()
                visitation.id = cursor.getInt(0)
                visitation.placeId=cursor.getInt(cursor.getColumnIndex(PLACEID))
                visitation.description = cursor.getString(cursor.getColumnIndex(DESCRIPTION))
                visitation.date = cursor.getString(cursor.getColumnIndex(DATE))

                visitationList.add(visitation)
            } while (cursor.moveToNext())

        }
        close()
        return visitationList
    }
}