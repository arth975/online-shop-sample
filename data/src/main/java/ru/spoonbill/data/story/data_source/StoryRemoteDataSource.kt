package ru.spoonbill.data.story.data_source

import kotlinx.coroutines.delay
import ru.spoonbill.data.story.model.Story

private val stories = listOf(
    Story(1), Story(2), Story(3), Story(4), Story(5), Story(6), Story(7),
)

class StoryRemoteDataSource : StoryDataSource {
    override suspend fun fetchStories(): List<Story> {
        delay(3000)
        return stories
    }
}