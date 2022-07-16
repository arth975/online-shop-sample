package ru.spoonbill.droid.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.databinding.ItemProductCardBinding
import ru.spoonbill.droid.common.extensions.loadCenterCropImage
import ru.spoonbill.droid.ui.home.entity.ProductUi

class ProductCardAdapter(
    private val onItemClick: (ProductUi) -> Unit,
    private val onFavoriteStateChange: (ProductUi, Boolean) -> Unit,
    private val onCartItemsCountClick: () -> Unit
) : ListAdapter<ProductUi, ProductCardAdapter.ItemViewHolder>(PRODUCTS_COMPARATOR) {

    inner class ItemViewHolder(private val binding: ItemProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductUi) {
            binding.product = item
            binding.root.setOnClickListener { onItemClick(item) }
            binding.imageProduct.loadCenterCropImage(item.imageUrl)
            binding.checkFavorite.isChecked = item.isFavorite
            binding.checkFavorite.setOnCheckedChangeListener { _, isChecked -> onFavoriteStateChange(item, isChecked) }
            setupCartItemsCount(item.quantityInCart)
        }

        private fun setupCartItemsCount(quantityInCart: Int) {
            if(quantityInCart > 0) {
                binding.textCartItemsCount.visibility = View.VISIBLE
                binding.textCartItemsCount.text = quantityInCart.toString()
                binding.textCartItemsCount.setOnClickListener { onCartItemsCountClick() }
            } else {
                binding.textCartItemsCount.visibility = View.GONE
                binding.textCartItemsCount.text = ""
                binding.textCartItemsCount.setOnClickListener { }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<ProductUi>() {
            override fun areItemsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean =
                oldItem == newItem
        }
    }
}