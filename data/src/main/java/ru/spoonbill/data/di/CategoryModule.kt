package ru.spoonbill.data.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.spoonbill.data.category.data_source.CategoryDataSource
import ru.spoonbill.data.category.data_source.CategoryRemoteDataSource
import ru.spoonbill.data.category.repository.CategoryRepository
import ru.spoonbill.data.category.repository.CategoryRepositoryImpl

val categoryModule = module {
    single { CategoryRemoteDataSource() } bind CategoryDataSource::class
    factory { CategoryRepositoryImpl(mDataSource = get()) } bind CategoryRepository::class
}