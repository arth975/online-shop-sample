package ru.spoonbill.data.category.model

data class ParentCategory(
    val id: Long,
    val name: String,
    val childCategories: List<Category>
)