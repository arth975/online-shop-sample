package ru.spoonbill.data.story.repository

import ru.spoonbill.data.story.model.Story

interface StoryRepository {

    suspend fun fetchStories(): List<Story>
}