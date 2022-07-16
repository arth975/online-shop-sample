package ru.spoonbill.app.common.widgets.button

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import ru.spoonbill.app.common.R

class SpoonbillCircleButton(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {

    init {
        setBackgroundResource(R.drawable.background_circle_button)
        setTextColor(ContextCompat.getColor(context, R.color.font_black))
        gravity = Gravity.CENTER
        typeface = ResourcesCompat.getFont(context, R.font.open_sans_semi_bold)

        setupInsets()
    }

    private fun setupInsets() {
        offsetTopAndBottom(0)
        minHeight = 0
        minWidth = 0
    }
}