package ru.spoonbill.data.category.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.spoonbill.data.category.model.ParentCategory

interface CategoryRepository {

    suspend fun fetchCategories(dispatcher: CoroutineDispatcher = Dispatchers.IO): List<ParentCategory>
}