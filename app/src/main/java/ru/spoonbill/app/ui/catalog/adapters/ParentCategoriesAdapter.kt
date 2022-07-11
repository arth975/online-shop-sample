package ru.spoonbill.app.ui.catalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.common.GridSpacingItemDecoration
import ru.spoonbill.app.databinding.ItemCategoryParentBinding
import ru.spoonbill.app.ui.catalog.model.ParentCategoryUi

class ParentCategoriesAdapter(
    private val mParentCategories: List<ParentCategoryUi>,
    private val mSpacing: Int
) : RecyclerView.Adapter<ParentCategoriesAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(private val mBinding: ItemCategoryParentBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(item: ParentCategoryUi) = with(mBinding.rvCatalogs) {
            mBinding.textHeader.text = item.name
            layoutManager = GridLayoutManager(mBinding.root.context, 2)
            adapter = CategoryAdapter(item.childCategories)
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
            addItemDecoration(GridSpacingItemDecoration(2, mSpacing, false))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCategoryParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mParentCategories[position])
    }

    override fun getItemCount(): Int = mParentCategories.size
}