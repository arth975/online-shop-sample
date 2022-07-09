package ru.spoonbill.app.ui.home.image_slider

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ru.spoonbill.app.autoslider.SliderViewAdapter
import ru.spoonbill.app.databinding.ItemImageSliderViewBinding

class ImageSliderAdapter(
    private val mItems: List<ImageSliderItem>
) : SliderViewAdapter<ImageSliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(private val mBinding: ItemImageSliderViewBinding) :
        SliderViewAdapter.ViewHolder(mBinding.root) {
        fun bind(item: ImageSliderItem) {
            Glide.with(mBinding.root.context)
                .load(item.uri)
                .centerCrop()
                .into(mBinding.sliderImage)
        }
    }

    override fun getCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        return SliderViewHolder(
            ItemImageSliderViewBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        viewHolder?.bind(mItems[position])
    }
}