package com.example.gezirehberim.model

import kotlin.properties.Delegates

class Visitation {
    var id by Delegates.notNull<Int>()
    var placeId by Delegates.notNull<Int>()
    lateinit var date: String
    lateinit var description: String
    var pictureList = ArrayList<Picture>()
}