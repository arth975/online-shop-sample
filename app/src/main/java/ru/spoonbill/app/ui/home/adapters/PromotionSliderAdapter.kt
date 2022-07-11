package ru.spoonbill.app.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ru.spoonbill.app.autoslider.SliderViewAdapter
import ru.spoonbill.app.databinding.ItemPromotionSliderViewBinding
import ru.spoonbill.app.ui.home.model.PromotionUi

class PromotionSliderAdapter(
    private val mItems: List<PromotionUi>
) : SliderViewAdapter<PromotionSliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(private val mBinding: ItemPromotionSliderViewBinding) :
        SliderViewAdapter.ViewHolder(mBinding.root) {
        fun bind(item: PromotionUi) {
            Glide.with(mBinding.root.context)
                .load(item.uri)
                .centerCrop()
                .into(mBinding.sliderImage)
        }
    }

    override fun getCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        return SliderViewHolder(
            ItemPromotionSliderViewBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        viewHolder?.bind(mItems[position])
    }
}