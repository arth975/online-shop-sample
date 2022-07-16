package ru.spoonbill.app.common.widgets.price_counter

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import ru.spoonbill.app.common.R
import ru.spoonbill.app.common.databinding.LayoutPriceCounterBinding

class SpoonbillPriceCounter(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var onCounterChangedListener: OnCounterChangedListener? = null
    private lateinit var binding: LayoutPriceCounterBinding
    private var counter = 0

    init {
        inflate(context, R.layout.layout_price_counter, this)
        orientation = HORIZONTAL

        setupBinding()
        setupListeners()
    }

    private fun setupBinding() {
        binding = LayoutPriceCounterBinding.bind(this)
        binding.editTextCounter.setText(counter.toString())
    }

    private fun setupListeners() {
        binding.buttonDecrease.setOnClickListener {
            if(counter > 0) changeCounterValue(--counter)
        }

        binding.buttonIncrease.setOnClickListener {
            changeCounterValue(++counter)
        }
    }

    private fun changeCounterValue(count: Int) {
        binding.editTextCounter.setText(count.toString())
        onCounterChangedListener?.onCounterChanged(count)
    }

    fun setOnCounterChangedListener(onCounterChangedListener: OnCounterChangedListener) {
        this.onCounterChangedListener = onCounterChangedListener
    }

    fun setInitialCount(count: Int) {
        counter = count
        binding.editTextCounter.setText(count.toString())
    }

    fun interface OnCounterChangedListener {
        fun onCounterChanged(count: Int)
    }
}