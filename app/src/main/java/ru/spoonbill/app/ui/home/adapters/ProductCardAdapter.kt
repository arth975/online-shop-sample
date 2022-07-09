package ru.spoonbill.app.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.databinding.ItemProductCardBinding
import ru.spoonbill.app.model.ProductUI

class ProductCardAdapter(
    private val mProductItems: List<ProductUI>
) : RecyclerView.Adapter<ProductCardAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val mBinding: ItemProductCardBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(productItem: ProductUI) {
            mBinding.product = productItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mProductItems[position])
    }

    override fun getItemCount(): Int = mProductItems.size

}