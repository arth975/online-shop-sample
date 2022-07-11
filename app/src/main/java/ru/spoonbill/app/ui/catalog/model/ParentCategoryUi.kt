package ru.spoonbill.app.ui.catalog.model

data class ParentCategoryUi(
    val id: Long,
    val name: String,
    val childCategories: List<ChildCategoryUi>
)
