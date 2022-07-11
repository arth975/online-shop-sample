package ru.spoonbill.app.ui.catalog.model

import ru.spoonbill.data.category.model.Category
import ru.spoonbill.data.category.model.ParentCategory

fun ParentCategory.toUi() = ParentCategoryUi(
    id = this.id,
    name = this.name,
    childCategories = this.childCategories.map(Category::toUi)
)

fun Category.toUi() = ChildCategoryUi(
    id = this.id,
    name = this.name,
    imageUri = this.imageUri
)