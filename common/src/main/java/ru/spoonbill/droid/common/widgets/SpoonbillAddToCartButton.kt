package ru.spoonbill.app.common.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import ru.spoonbill.app.common.R
import ru.spoonbill.app.common.databinding.ButtonAddToCartBinding

class SpoonbillAddToCartButton(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: ButtonAddToCartBinding

    init {
        inflate(context, R.layout.button_add_to_cart, this)
        binding = ButtonAddToCartBinding.bind(this)
    }

    fun setPrice(price: Int) {
        binding.textPrice.text = "$price Руб."
    }
}