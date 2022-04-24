package com.example.gezirehberim.model

import kotlin.properties.Delegates

class Place {
    var id by Delegates.notNull<Int>()
    lateinit var name: String
    lateinit var locationDefinition: String
    lateinit var description: String
    lateinit var priority: Priority
    var pictureList = ArrayList<String>()
    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()
    var visitation = ArrayList<Visitation>()
    //sqlite boolean değer almadığı için 0 ve 1 kullanarak ziyaret durumu kontrol edilecek
    var isVisited=0
}