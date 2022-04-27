package com.example.gezirehberim.logic

import android.content.Context
import com.example.gezirehberim.DAL.PictureOperation
import com.example.gezirehberim.DAL.PlaceOperation
import com.example.gezirehberim.DAL.VisitationOperation
import com.example.gezirehberim.model.Place
import com.example.gezirehberim.model.Visitation
import com.example.gezirehberim.view.activity.MainActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VisitationLogic {
    companion object {
        @OptIn(DelicateCoroutinesApi::class)
        fun addVisitation( visitation: Visitation): Long {
            val visitationOperation = VisitationOperation(MainActivity._context!!)
            val pictureOperation = PictureOperation(MainActivity._context!!)
            val id = visitationOperation.addVisitation(visitation)
            GlobalScope.launch {
                for (picture in visitation.pictureList) {
                    picture.visitationId = id.toInt()
                    pictureOperation.addPicture(picture)

                }
            }
            return id
        }
        fun getVisitationList(placeId:Int): ArrayList<Visitation> {
            val list=VisitationOperation(MainActivity._context!!).getVisitation(placeId)

            for(visitation in list){
                visitation.pictureList=PictureOperation(MainActivity._context!!).getPictures(visitation.id,null)
            }
            return list
        }
    }
}