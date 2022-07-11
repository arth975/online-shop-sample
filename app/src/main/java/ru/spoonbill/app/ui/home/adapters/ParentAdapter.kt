package ru.spoonbill.app.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.autoslider.IndicatorView.animation.type.IndicatorAnimationType
import ru.spoonbill.app.autoslider.SliderAnimations
import ru.spoonbill.app.databinding.ItemProductParentBinding
import ru.spoonbill.app.databinding.ItemPromotionSliderBinding
import ru.spoonbill.app.ui.home.model.ProductCollection
import ru.spoonbill.app.ui.home.model.PromotionUi

class ParentAdapter(
    private val mProductListItems: List<ProductCollection>,
    private val mSliderItems: List<PromotionUi>
) : RecyclerView.Adapter<ParentAdapter.ItemViewHolder<*>>() {

    private val mPool by lazy { RecyclerView.RecycledViewPool() }

    abstract class ItemViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: T)
    }

    inner class ParentViewHolder(private val mBinding: ItemProductParentBinding) :
        ItemViewHolder<ProductCollection>(mBinding.root) {
        override fun bind(item: ProductCollection) {
            mBinding.textListName.text = item.name
            //mBinding.rvProducts.setRecycledViewPool(mPool)
            mBinding.rvProducts.setHasFixedSize(true)
            mBinding.rvProducts.adapter = ProductCardAdapter(item.items)
            mBinding.rvProducts.layoutManager = LinearLayoutManager(mBinding.root.context).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }
    }

    inner class SliderViewHolder(private val mBinding: ItemPromotionSliderBinding) :
        ItemViewHolder<List<PromotionUi>>(mBinding.root) {
        override fun bind(items: List<PromotionUi>) {
            val adapter = PromotionSliderAdapter(items)
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
                    ItemPromotionSliderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            else ->
                ParentViewHolder(
                    ItemProductParentBinding.inflate(
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