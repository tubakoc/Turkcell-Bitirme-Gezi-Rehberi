package com.example.gezirehberim.model

import kotlin.properties.Delegates

class Picture {
    var id by Delegates.notNull<Int>()
    var placeId: Int? = null
    var visitationId: Int? = null
    lateinit var data: String

}