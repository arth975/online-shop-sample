package ru.spoonbill.app.ui.catalog.model

import ru.spoonbill.app.ui.home.model.ProductUi

data class CatalogResult(
    val parentCategories: List<ParentCategoryUi>,
    val recommendedProducts: List<ProductUi>
)
