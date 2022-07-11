package ru.spoonbill.app.ui.catalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.spoonbill.app.databinding.ItemCategoryBinding
import ru.spoonbill.app.ui.catalog.model.ChildCategoryUi

class CategoryAdapter(
    private val mCategoryItems: List<ChildCategoryUi>
) : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val mBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(item: ChildCategoryUi) {
            mBinding.textName.text = item.name
            Glide.with(mBinding.root.context)
                .load(item.imageUri)
                .centerCrop()
                .into(mBinding.imageCategory)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mCategoryItems[position])
    }

    override fun getItemCount(): Int = mCategoryItems.size
}