package ru.spoonbill.app.common.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import ru.spoonbill.app.common.R

class SwipeDownLine(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var startX = 0f
    private var startY = 0f
    private var endX = 0f
    private var endY = 0f
    private val paint: Paint
    private lateinit var rectF: RectF
    private val cornerRadius: Float
    private val height: Float

    init {
        cornerRadius = context.resources.getDimension(R.dimen.swipe_down_line_corner_radius)
        height = context.resources.getDimension(R.dimen.swipe_down_line_size)
        paint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.swipeDownLine)
            style = Paint.Style.FILL
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas?) {
        rectF = RectF(
            startX, startY,
            startX + measuredWidth,//startX + measuredWidth,
            startY + measuredHeight
        )
        canvas?.drawRoundRect(rectF, cornerRadius, cornerRadius, paint)
    }
}