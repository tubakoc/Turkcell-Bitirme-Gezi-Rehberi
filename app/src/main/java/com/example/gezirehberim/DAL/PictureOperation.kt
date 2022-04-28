package com.example.gezirehberim.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.gezirehberim.model.Picture
import com.example.gezirehberim.model.Visitation

class PictureOperation(context: Context) {
    var pictureDatabase: SQLiteDatabase? = null
    private val PLACEID = "PlaceId"
    private val VISITATIONID = "VisitationId"
    private val DATA = "Data"
    private val TABLENAME = "Picture"
    var dbOpenHelper: DatabaseOpenHelper

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "TripApp", null, 1)
    }

    private fun open() {

        pictureDatabase = dbOpenHelper.writableDatabase
    }

    private fun close() {
        if (pictureDatabase != null && pictureDatabase!!.isOpen) {
            pictureDatabase!!.close()
        }
    }

    fun addPicture(picture: Picture): Long {
        val contentValues = ContentValues()
        contentValues.put(DATA, picture.data)
        contentValues.put(PLACEID, picture.placeId)
        contentValues.put(VISITATIONID, picture.visitationId)
        open()
        val record = pictureDatabase!!.insert(TABLENAME, null, contentValues)
        close()
        return record

    }

    fun deletePicture(id: Int) {

        open()
        pictureDatabase!!.delete(TABLENAME, "Id = ?", arrayOf(id.toString()))
        close()
    }


    private fun getPicturesByPlaceId(id: Int): Cursor {
        val query = "Select * from $TABLENAME where PlaceId=?"
        return pictureDatabase!!.rawQuery(query, arrayOf(id.toString()))
    }

    private fun getPicturesByVisitationId(id: Int): Cursor {
        val query = "Select * from $TABLENAME where VisitationId=?"
        return pictureDatabase!!.rawQuery(query, arrayOf(id.toString()))
    }


    @SuppressLint("Range")
    fun getPictures(visitationId: Int?, PlaceId: Int?): ArrayList<Picture> {
        open()
        val pictureList = ArrayList<Picture>()
        var picture: Picture
        val cursor: Cursor = if (visitationId != null) {
            getPicturesByVisitationId(visitationId)
        } else {
            getPicturesByPlaceId(PlaceId!!)
        }


        if (cursor.moveToFirst()) {
            do {
                picture = Picture()
                picture.id = cursor.getInt(0)
                picture.placeId = cursor.getInt(cursor.getColumnIndex(PLACEID))
                picture.visitationId = cursor.getInt(cursor.getColumnIndex(VISITATIONID))
                picture.data = cursor.getString(cursor.getColumnIndex(DATA))

                pictureList.add(picture)
            } while (cursor.moveToNext())

        }
        close()
        return pictureList
    }
}