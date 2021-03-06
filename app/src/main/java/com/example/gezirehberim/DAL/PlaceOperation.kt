package com.example.gezirehberim.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.gezirehberim.logic.VisitationLogic
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.model.Priority

class PlaceOperation(context: Context) {
    var placeDatabase: SQLiteDatabase? = null
    private val NAME = "Name"
    private val LOCATIONDEFINITION = "LocationDefinition"
    private val DESCRIPTION = "Description"
    private val PRIORITY = "Priority"
    private val LATITUDE = "Latitude"
    private val LONGITUDE = "Longitude"
    private val ISVISITED = "IsVisited"
    private val TABLENAME = "Place"
    private val LASTVISIT="LastVisit"

    var dbOpenHelper: DatabaseOpenHelper

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "TripApp", null, 1)
    }

    private fun open() {

        placeDatabase = dbOpenHelper.writableDatabase
    }

    private fun close() {
        if (placeDatabase != null && placeDatabase!!.isOpen) {
            placeDatabase!!.close()
        }
    }

    fun setVisit(id: Int, visited: Int) {
        val contentValues = ContentValues()

        contentValues.put(ISVISITED, visited)
        open()
      //placeDatabase!!.execSQL("Update $TABLENAME SET $ISVISITED =CASE WHEN $ISVISITED=0 THEN $ISVISITED=1 END WHERE Id=$id  ")
        placeDatabase!!.update(
            TABLENAME,

            contentValues,
            "Id = ?",
            arrayOf(id.toString())
        )
        close()

    }

    fun addPlace(place: Place): Long {
        val contentValues = ContentValues()
        contentValues.put(NAME, place.name)
        contentValues.put(LOCATIONDEFINITION, place.locationDefinition)
        contentValues.put(DESCRIPTION, place.description)
        contentValues.put(PRIORITY, place.priority)
        contentValues.put(LATITUDE, place.latitude)
        contentValues.put(LONGITUDE, place.longitude)
        contentValues.put(ISVISITED, place.isVisited)

        open()
        val record = placeDatabase!!.insert(TABLENAME, null, contentValues)
        close()
        return record

    }

    fun updatePlace(place: Place) {

        val contentValues = ContentValues()
        contentValues.put(NAME, place.name)
        contentValues.put(LOCATIONDEFINITION, place.locationDefinition)
        contentValues.put(DESCRIPTION, place.description)
        contentValues.put(PRIORITY, place.priority)
        contentValues.put(LATITUDE, place.latitude)
        contentValues.put(LONGITUDE, place.longitude)
        contentValues.put(ISVISITED, place.isVisited)

        open()
        placeDatabase!!.update(
            TABLENAME,
            contentValues,
            "Id = ?",
            arrayOf(place.id.toString())
        )
        close()


    }

    fun deletePlace(id: Int) {

        open()
        placeDatabase!!.delete(TABLENAME, "Id = ?", arrayOf(id.toString()))
        close()
    }

    private fun getAllPlaces(isVisited: Int): Cursor {
        val query = "Select * from $TABLENAME where IsVisited=? order by id desc"
        return placeDatabase!!.rawQuery(query, arrayOf(isVisited.toString()))
    }

    private fun getPlaceById(id: Int): Cursor {
        val query = "Select * from $TABLENAME where Id=?"
        return placeDatabase!!.rawQuery(query, arrayOf(id.toString()))
    }


    @SuppressLint("Range")
    fun getPlace(id: Int?, isVisited: Int?): ArrayList<Place> {
        open()
        val placeList = ArrayList<Place>()
        var place: Place
        val cursor: Cursor = if (id != null) {
            getPlaceById(id)
        } else {
            getAllPlaces(isVisited!!)
        }


        if (cursor.moveToFirst()) {
            do {
                place = Place()
                place.id = cursor.getInt(0)
                place.name = cursor.getString(cursor.getColumnIndex(NAME))
                place.description = cursor.getString(cursor.getColumnIndex(DESCRIPTION))
                place.locationDefinition =
                    cursor.getString(cursor.getColumnIndex(LOCATIONDEFINITION))
                place.priority = cursor.getInt(cursor.getColumnIndex(PRIORITY))
                place.isVisited = cursor.getInt(cursor.getColumnIndex(ISVISITED))
                place.latitude = cursor.getDouble(cursor.getColumnIndex(LATITUDE))
                place.longitude = cursor.getDouble(cursor.getColumnIndex(LONGITUDE))
                place.visitationList = VisitationLogic.getVisitationList(place.id)

                placeList.add(place)
            } while (cursor.moveToNext())

        }
        close()
        return placeList
    }
}