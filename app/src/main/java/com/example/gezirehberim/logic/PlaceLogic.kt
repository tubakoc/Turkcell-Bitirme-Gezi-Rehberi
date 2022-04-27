package com.example.gezirehberim.logic

import android.content.Context
import com.example.gezirehberim.DAL.PictureOperation
import com.example.gezirehberim.DAL.PlaceOperation
import com.example.gezirehberim.DAL.VisitationOperation
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.model.Visitation

class PlaceLogic {
    companion object {
        fun addPlace(context: Context, place: Place): Long {
            val placeOperation = PlaceOperation(context)
            val pictureOperation = PictureOperation(context)
            val id = placeOperation.addPlace(place)
            for (picture in place.pictureList) {
                picture.placeId = id.toInt()
                pictureOperation.addPicture(picture)

            }


            return id
        }

        fun getPlaceList(context: Context): ArrayList<Place> {
            val list=PlaceOperation(context).getPlace(null)
            for(place in list){
                place.pictureList=PictureOperation(context).getPictures(null,place.id)
            }
            return list
        }
        fun setVisit(context: Context,place: Place){
            if(place.isVisited==0){
                val placeOperation=PlaceOperation(context)
                placeOperation.setVisit(place.id,1)
            }

        }
        fun getPlaceDetail(context: Context, id: Int): Place {
            val place=PlaceOperation(context).getPlace(id)[0]


               val visitationList=VisitationOperation(context).getVisitation(id)
                for (visitation in visitationList){
                    visitation.pictureList=PictureOperation(context).getPictures(visitation.id,null)
            }
            place.visitationList=visitationList
            return place
        }
    }
}