package ru.spoonbill.app.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.autoslider.IndicatorView.animation.type.IndicatorAnimationType
import ru.spoonbill.app.autoslider.SliderAnimations
import ru.spoonbill.app.databinding.ItemImageSliderBinding
import ru.spoonbill.app.databinding.ItemProductListBinding
import ru.spoonbill.app.model.ProductListItem
import ru.spoonbill.app.ui.home.image_slider.ImageSliderAdapter
import ru.spoonbill.app.ui.home.image_slider.ImageSliderItem

class ParentAdapter(
    private val mProductListItems: List<ProductListItem>,
    private val mSliderItems: List<ImageSliderItem>
) : RecyclerView.Adapter<ParentAdapter.ItemViewHolder<*>>() {

    private val mPool by lazy { RecyclerView.RecycledViewPool() }

    abstract class ItemViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: T)
    }

    inner class ParentViewHolder(private val mBinding: ItemProductListBinding) :
        ItemViewHolder<ProductListItem>(mBinding.root) {
        override fun bind(item: ProductListItem) {
            mBinding.textListName.text = item.name
            mBinding.rvProducts.setRecycledViewPool(mPool)
            //mBinding.rvProducts.setHasFixedSize(true)
            mBinding.rvProducts.adapter = ProductCardAdapter(item.items)
            mBinding.rvProducts.layoutManager = LinearLayoutManager(mBinding.root.context).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }
    }

    inner class SliderViewHolder(private val mBinding: ItemImageSliderBinding) :
        ItemViewHolder<List<ImageSliderItem>>(mBinding.root) {
        override fun bind(items: List<ImageSliderItem>) {
            val adapter = ImageSliderAdapter(items)
            mBinding.textListName.text = "Акции"
            with(mBinding.slider) {
                setIndicatorAnimation(IndicatorAnimationType.WORM)
                setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                setSliderAdapter(adapter)
                startAutoCycle()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<*> {
        return when (viewType) {
            VIEW_TYPE_SLIDER ->
                SliderViewHolder(
                    ItemImageSliderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            else ->
                ParentViewHolder(
                    ItemProductListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder<*>, position: Int) {
        when (holder) {
            is ParentViewHolder ->
                holder.bind(mProductListItems[position - 1])
            is SliderViewHolder ->
                holder.bind(mSliderItems)
        }
    }

    override fun getItemCount(): Int = mProductListItems.size + 1

    override fun getItemViewType(position: Int): Int =
        if (position != 0) VIEW_TYPE_PARENT else VIEW_TYPE_SLIDER

    companion object {
        private const val VIEW_TYPE_SLIDER = 0
        private const val VIEW_TYPE_PARENT = 1
    }
}