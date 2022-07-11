package ru.spoonbill.data.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.spoonbill.data.story.data_source.StoryDataSource
import ru.spoonbill.data.story.data_source.StoryRemoteDataSource
import ru.spoonbill.data.story.repository.StoryRepository
import ru.spoonbill.data.story.repository.StoryRepositoryImpl

val storyModule = module {
    single { StoryRemoteDataSource() } bind StoryDataSource::class
    factory { StoryRepositoryImpl(mDataSource = get()) } bind StoryRepository::class
}