package ru.spoonbill.data.category.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.spoonbill.data.category.data_source.CategoryDataSource
import ru.spoonbill.data.category.model.ParentCategory

class CategoryRepositoryImpl(
    private val mDataSource: CategoryDataSource
) : CategoryRepository {
    override suspend fun fetchCategories(
        dispatcher: CoroutineDispatcher
    ): List<ParentCategory> = withContext(dispatcher) {
        mDataSource.fetchCategories()
    }
}