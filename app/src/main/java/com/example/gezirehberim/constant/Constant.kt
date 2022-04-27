package com.example.gezirehberim.constant

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import androidx.annotation.ColorRes
import com.example.gezirehberim.view.activity.MainActivity.Companion._context
import com.example.gezirehberim.R


class Constant {


    companion object {

        const val TO_BE_VISITED_LIST_ID=0
        const val VISITED_LIST_ID=1
        val priorities = Priority.getPriorities()


    }

}

object Priority {

    @ColorRes
    val colorList = listOf<Int>(
        R.color.priority_one,
        R.color.priority_two,
        R.color.priority_three,
    )

    fun getPriorities(): List<ShapeDrawable> {
        val radius = 50f
        val shapeList = arrayListOf<ShapeDrawable>()
        colorList.forEach {
            val shape =
                ShapeDrawable(
                    RoundRectShape(
                        floatArrayOf(
                            radius,
                            radius,
                            radius,
                            radius,
                            radius,
                            radius,
                            radius,
                            radius
                        ), null, null
                    )
                )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                shape.paint.color = _context?.resources?.getColor(it,_context?.theme)!!
            }else{
                shape.paint.color = _context?.resources?.getColor(it)!!
            }
            shapeList.add(shape)
        }
        return shapeList
    }


}