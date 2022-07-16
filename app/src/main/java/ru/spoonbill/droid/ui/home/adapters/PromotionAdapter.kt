package ru.spoonbill.droid.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.databinding.ItemPromotionsBinding
import ru.spoonbill.droid.common.extensions.loadCenterCropImage
import ru.spoonbill.droid.ui.home.entity.PromotionUi

class PromotionAdapter : ListAdapter<PromotionUi, PromotionAdapter.ItemViewHolder>(PROMOTIONS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemPromotionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: ItemPromotionsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PromotionUi) {
            binding.promotionImage.loadCenterCropImage(item.uri)
            binding.promotionDescription.text = item.description
        }
    }

    companion object {
        val PROMOTIONS_COMPARATOR = object : DiffUtil.ItemCallback<PromotionUi>() {
            override fun areItemsTheSame(oldItem: PromotionUi, newItem: PromotionUi): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PromotionUi, newItem: PromotionUi): Boolean =
                oldItem == newItem
        }
    }
}