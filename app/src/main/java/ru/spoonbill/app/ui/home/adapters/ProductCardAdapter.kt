package ru.spoonbill.app.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.databinding.ItemProductCardBinding
import ru.spoonbill.app.ui.home.model.ProductUi

class ProductCardAdapter(
    private val mProductItems: List<ProductUi>
) : RecyclerView.Adapter<ProductCardAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val mBinding: ItemProductCardBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        private var mCounter = 0
        fun bind(productItem: ProductUi) {
            mBinding.product = productItem
            mBinding.textCounter.text = mCounter.toString()
            mBinding.textPlus.setOnClickListener {
                mCounter++
                mBinding.textCounter.text = mCounter.toString()
            }
            mBinding.textMinus.setOnClickListener {
                if(mCounter > 0) {
                    mCounter--
                    mBinding.textCounter.text = mCounter.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mProductItems[position])
    }

    override fun getItemCount(): Int = mProductItems.size

}