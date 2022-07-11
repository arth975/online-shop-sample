package ru.spoonbill.app.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.databinding.ItemStoryBinding
import ru.spoonbill.app.ui.home.model.StoryUi

class StoryAdapter(
    private val mProductItems: List<StoryUi>
) : RecyclerView.Adapter<StoryAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val mBinding: ItemStoryBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = mProductItems.size
}