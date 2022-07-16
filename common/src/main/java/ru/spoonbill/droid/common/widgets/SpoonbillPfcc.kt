package ru.spoonbill.droid.common.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import ru.spoonbill.app.common.R

class SpoonbillPfcc(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.layout_spoonbill_pfcc, this)
    }
}