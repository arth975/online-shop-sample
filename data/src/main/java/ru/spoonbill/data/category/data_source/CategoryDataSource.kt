package ru.spoonbill.data.category.data_source

import ru.spoonbill.data.category.model.ParentCategory

interface CategoryDataSource {
    suspend fun fetchCategories(): List<ParentCategory>
}