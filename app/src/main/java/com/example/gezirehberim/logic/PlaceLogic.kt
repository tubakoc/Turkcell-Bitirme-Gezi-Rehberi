package com.example.gezirehberim.logic

import com.example.gezirehberim.DAL.PictureOperation
import com.example.gezirehberim.DAL.PlaceOperation
import com.example.gezirehberim.DAL.VisitationOperation
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.view.activity.MainActivity.Companion._context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlaceLogic {
    companion object {
        fun addPlace(place: Place): Long {
            val placeOperation = PlaceOperation(_context!!)
            val pictureOperation = PictureOperation(_context!!)
            val id = placeOperation.addPlace(place)
            for (picture in place.pictureList) {
                GlobalScope.launch {
                    picture.placeId = id.toInt()
                    pictureOperation.addPicture(picture)
                }

            }
            return id
        }

        fun getPlaceList(isVisited: Int): ArrayList<Place> {
            var list=PlaceOperation(_context!!).getPlace(null,isVisited)

            for(place in list){
                place.pictureList=PictureOperation(_context!!).getPictures(null,place.id)
            }
            return list
        }
        fun setVisit(placeId: Int, date: String){

                val placeOperation=PlaceOperation(_context!!)
                placeOperation.setVisit(placeId,1)


        }
        fun getPlaceDetail( id: Int): Place {
            val place=PlaceOperation(_context!!).getPlace(id,null)[0]
            place.pictureList=PictureOperation(_context!!).getPictures(null,place.id)

                place.pictureList=PictureOperation(_context!!).getPictures(null,place.id)

               val visitationList=VisitationOperation(_context!!).getVisitation(id)
                for (visitation in visitationList){
                    visitation.pictureList=PictureOperation(_context!!).getPictures(visitation.id,null)
            }
            place.visitationList=visitationList
            return place
        }
    }
}