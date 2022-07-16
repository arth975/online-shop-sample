package ru.spoonbill.droid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.spoonbill.droid.data.core.features.product.entity.Product
import ru.spoonbill.droid.data.core.features.product.entity.Promotion
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepository
import ru.spoonbill.droid.mappers.toModel
import ru.spoonbill.droid.mappers.toUi
import ru.spoonbill.droid.ui.home.entity.*

class HomeViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun initialize() {
        fetchData()
    }

    fun changeFavoriteStatus(product: ProductUi, isFavorite: Boolean) {
        viewModelScope.launch {
            val model = product.copy(isFavorite = isFavorite).toModel()
            productRepository.updateProduct(model)
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                launch { fetchRecommendedProducts() }
                launch { fetchPopularProducts() }
                launch { fetchActualProducts() }
                launch { fetchPromotions() }
            } catch (e: Exception) {
                _uiStateFlow.update { value -> value.copy(errorMessages = listOf(e.message)) }
            }
        }
    }

    private suspend fun fetchRecommendedProducts() {
        productRepository.getRecommendedProductsFlow().collectLatest { products ->
            _uiStateFlow.update { value -> value.copy(recommendedProducts = products.map(Product::toUi)) }
        }
    }

    private suspend fun fetchPopularProducts() {
        productRepository.getPopularProductsFlow().collectLatest { products ->
            _uiStateFlow.update { value -> value.copy(popularProducts = products.map(Product::toUi)) }
        }
    }

    private suspend fun fetchActualProducts() {
        productRepository.getActualProductsFlow().collectLatest { products ->
            _uiStateFlow.update { value -> value.copy(actualProducts = products.map(Product::toUi)) }
        }
    }

    private suspend fun fetchPromotions(dispatcher: CoroutineDispatcher = Dispatchers.IO) = withContext(dispatcher) {
        _uiStateFlow.update { value -> value.copy(promotions = productRepository.getPromotions().map(Promotion::toUi))  }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val recommendedProducts: List<ProductUi> = listOf(),
        val popularProducts: List<ProductUi> = listOf(),
        val actualProducts: List<ProductUi> = listOf(),
        val stories: List<StoryUi> = listOf(),
        val promotions: List<PromotionUi> = listOf(),
        val errorMessages: List<String?>? = null,
    )
}