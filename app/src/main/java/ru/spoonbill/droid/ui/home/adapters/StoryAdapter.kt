package ru.spoonbill.droid.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.droid.common.extensions.loadImage
import ru.spoonbill.app.databinding.ItemStoryBinding
import ru.spoonbill.droid.common.extensions.loadCenterCropImage
import ru.spoonbill.droid.ui.home.entity.StoryUi

class StoryAdapter : ListAdapter<StoryUi, StoryAdapter.ItemViewHolder>(STORIES_COMPARATOR) {

    class ItemViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoryUi) {
            binding.storyDescription.text = item.description
            binding.storyImage.loadCenterCropImage(item.imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val STORIES_COMPARATOR = object : DiffUtil.ItemCallback<StoryUi>() {
            override fun areItemsTheSame(oldItem: StoryUi, newItem: StoryUi): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: StoryUi, newItem: StoryUi): Boolean =
                oldItem == newItem
        }
    }
}