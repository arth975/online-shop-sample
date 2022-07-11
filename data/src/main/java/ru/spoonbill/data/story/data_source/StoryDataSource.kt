package ru.spoonbill.data.story.data_source

import ru.spoonbill.data.story.model.Story

interface StoryDataSource {

    suspend fun fetchStories(): List<Story>
}