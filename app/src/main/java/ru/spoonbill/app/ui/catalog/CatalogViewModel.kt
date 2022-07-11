package ru.spoonbill.app.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.spoonbill.app.ui.catalog.model.CatalogResult
import ru.spoonbill.app.ui.catalog.model.toUi
import ru.spoonbill.app.ui.home.model.toUi
import ru.spoonbill.app.utils.Resource
import ru.spoonbill.data.category.model.ParentCategory
import ru.spoonbill.data.category.repository.CategoryRepository
import ru.spoonbill.data.product.model.Product
import ru.spoonbill.data.product.repository.ProductRepository

class CatalogViewModel(
    private val mCategoryRepository: CategoryRepository,
    private val mProductRepository: ProductRepository
) : ViewModel() {

    private val mResultFlow = MutableStateFlow<Resource<CatalogResult>>(Resource.loading())
    val resultFlow = mResultFlow.asStateFlow()

    fun fetchResult() = viewModelScope.launch {
        try {
            val categories =
                async { mCategoryRepository.fetchCategories().map(ParentCategory::toUi) }
            val recommendedProducts =
                async { mProductRepository.fetchRecommendedProducts().map(Product::toUi) }
            val result = CatalogResult(
                categories.await(),
                recommendedProducts.await()
            )
            mResultFlow.value = Resource.success(result)
        } catch (e: Exception) {
            mResultFlow.value = Resource.failure(e)
        }
    }
}