package ru.spoonbill.droid.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.droid.common.extensions.loadImage
import ru.spoonbill.app.databinding.ItemProductBinding
import ru.spoonbill.droid.common.extensions.loadCenterCropImage
import ru.spoonbill.droid.ui.home.entity.ProductUi

class SimpleProductAdapter(
    private val onProductItemClick: (ProductUi) -> Unit,
    private val onFavoriteCheckedChange: (ProductUi, Boolean) -> Unit,
    private val onQuantityInCartChange: (ProductUi, Int) -> Unit
) : ListAdapter<ProductUi, SimpleProductAdapter.ItemViewHolder>(FavoriteProductItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ItemViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductUi) {
            binding.product = item
            binding.root.setOnClickListener { onProductItemClick(item) }
            binding.imageProduct.loadCenterCropImage(item.imageUrl)
            binding.spoonbillPriceCounter.setInitialCount(item.quantityInCart)
            binding.spoonbillPriceCounter.setOnCounterChangedListener { count -> onQuantityInCartChange(item, count) }
            binding.checkboxIsFavorite.setOnCheckedChangeListener { _, isChecked -> onFavoriteCheckedChange(item, isChecked) }
        }
    }

    class FavoriteProductItemCallback : DiffUtil.ItemCallback<ProductUi>() {
        override fun areItemsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean =
            oldItem.productId == newItem.productId

        override fun areContentsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean =
            oldItem == newItem
    }
}