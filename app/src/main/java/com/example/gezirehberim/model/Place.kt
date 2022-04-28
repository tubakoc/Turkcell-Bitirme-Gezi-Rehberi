package com.example.gezirehberim.model

import kotlin.properties.Delegates

class Place {
    var id by Delegates.notNull<Int>()
     var name: String?=null
     var locationDefinition: String?=null
     var description: String?=null
    var priority by Delegates.notNull<Int>()
    var pictureList = ArrayList<Picture>()
    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()
    var visitationList = ArrayList<Visitation>()
    //sqlite boolean değer almadığı için 0 ve 1 kullanarak ziyaret durumu kontrol edilecek
    var isVisited=0
}