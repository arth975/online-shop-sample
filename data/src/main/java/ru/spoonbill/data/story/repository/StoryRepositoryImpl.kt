package ru.spoonbill.data.story.repository

import ru.spoonbill.data.story.model.Story
import ru.spoonbill.data.story.data_source.StoryDataSource

class StoryRepositoryImpl(
    private val mDataSource: StoryDataSource
) : StoryRepository {
    override suspend fun fetchStories(): List<Story> {
        return mDataSource.fetchStories()
    }
}