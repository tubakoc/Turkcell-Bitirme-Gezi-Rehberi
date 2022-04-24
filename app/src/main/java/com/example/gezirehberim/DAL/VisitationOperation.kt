package com.example.gezirehberim.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.gezirehberim.model.Visitation

class VisitationOperation(context: Context) {
    var visitationDatabase: SQLiteDatabase? = null
    private val DATE = "Name"
    private val DESCRIPTION = "Description"
    private val TABLENAME = "Visitation"

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

    private fun getVisitationById(id: Int): Cursor {
        val query = "Select * from $TABLENAME where Id=?"
        return visitationDatabase!!.rawQuery(query, arrayOf(id.toString()))
    }


    @SuppressLint("Range")
    fun getVisitation(id: Int?): ArrayList<Visitation> {
        open()
        val visitationList = ArrayList<Visitation>()
        val visitation = Visitation()
        val cursor: Cursor = if (id != null) {
            getVisitationById(id)
        } else {
            getAllVisitations()
        }


        if (cursor.moveToFirst()) {
            do {

                visitation.id = cursor.getInt(0)
                visitation.description = cursor.getString(cursor.getColumnIndex(DESCRIPTION))
                visitation.date = cursor.getString(cursor.getColumnIndex(DATE))

                visitationList.add(visitation)
            } while (cursor.moveToNext())

        }
        close()
        return visitationList
    }
}