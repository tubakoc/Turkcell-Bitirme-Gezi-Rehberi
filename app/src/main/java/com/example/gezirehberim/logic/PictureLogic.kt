package com.example.gezirehberim.logic

import com.example.gezirehberim.DAL.PictureOperation
import com.example.gezirehberim.model.Picture
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.view.activity.MainActivity
import kotlinx.coroutines.DelicateCoroutinesApi

class PictureLogic {
    companion object {
        @OptIn(DelicateCoroutinesApi::class)
        fun addPicture(picture: Picture): Long {
            val pictureOperation = PictureOperation(MainActivity._context!!)

            return pictureOperation.addPicture(picture)
        }
        fun getPictureList(placeId: Int, visitationId: Int): ArrayList<Picture> {


            return PictureOperation(MainActivity._context!!).getPictures(placeId, visitationId)
        }
        fun returnPicturesForSlider(place: Place):ArrayList<Picture>{
            val visitationList=place.visitationList
            val pictureList=ArrayList<Picture>()
            for (visitation in visitationList){
                pictureList.addAll(
                    visitation.pictureList
                )
            }
            return pictureList
        }
    }
}