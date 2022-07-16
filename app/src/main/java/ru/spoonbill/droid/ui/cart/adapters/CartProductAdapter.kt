package ru.spoonbill.droid.ui.cart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.databinding.ItemCartProductBinding
import ru.spoonbill.droid.common.extensions.loadCenterCropImage
import ru.spoonbill.droid.ui.home.entity.ProductUi

class CartProductAdapter(
    private val onQuantityChanged: (ProductUi) -> Unit,
    private val onDeleteButtonClick: (ProductUi) -> Unit
) : ListAdapter<ProductUi, CartProductAdapter.ItemViewHolder>(CartProductItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCartProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ItemViewHolder(private val binding: ItemCartProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductUi) {
            with(binding) {
                cartProduct = item
                imageProduct.loadCenterCropImage(item.imageUrl)
                spoonbillPriceCounter.setOnCounterChangedListener { onQuantityChanged(item.copy(quantityInCart = it)) }
                spoonbillPriceCounter.setInitialCount(item.quantityInCart)
                buttonDelete.setOnClickListener { onDeleteButtonClick(item) }
            }
        }
    }

    class CartProductItemCallback : DiffUtil.ItemCallback<ProductUi>() {
        override fun areItemsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean =
            oldItem == newItem
    }
}