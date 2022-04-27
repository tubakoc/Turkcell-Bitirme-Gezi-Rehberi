package com.example.gezirehberim.model

import com.example.gezirehberim.R

enum class Priority {
    High{
//Renk kodları değişecek default dursun diye yazdım ana ekranda direkt getcoloru çağırıp renki implement edebilirz kolayca
        override fun getColorCode() = R.color.priority_one
        override fun getPriorityDegree()="Öncelik 1"
    },
    Medium{
        override fun getColorCode() = R.color.priority_two
        override fun getPriorityDegree()="Öncelik 2"
    },
    Low{
        override fun getColorCode() = R.color.priority_three
        override fun getPriorityDegree()="Öncelik 3"
    };
    abstract fun getColorCode(): Int
    abstract fun getPriorityDegree():String

}