package ru.spoonbill.droid.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.spoonbill.droid.data.core.features.product.entity.Product
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepository
import ru.spoonbill.droid.ui.favorites.FavoritesViewModel.UiState.*
import ru.spoonbill.droid.ui.home.entity.ProductUi
import ru.spoonbill.droid.mappers.toModel
import ru.spoonbill.droid.mappers.toUi

class FavoritesViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<UiState>(Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun initialize() {
        fetchFavoriteProducts()
    }

    fun changeFavoriteStatus(product: ProductUi, isFavorite: Boolean) {
        viewModelScope.launch {
            val model = product.copy(isFavorite = isFavorite).toModel()
            productRepository.updateProduct(model)
        }
    }

    fun changeQuantityInCart(product: ProductUi, quantity: Int) {
        viewModelScope.launch {
            val model = product.copy(quantityInCart = quantity).toModel()
            productRepository.updateProduct(model)
        }
    }

    private fun fetchFavoriteProducts() = viewModelScope.launch {
        try {
            var products: List<ProductUi>
            productRepository.getFavoriteProductsFlow().collectLatest { result ->
                products = result.map(Product::toUi)
                _uiStateFlow.value = Success(products)
            }
        } catch (e: Exception) {
            _uiStateFlow.value = Failure(e)
        }
    }

    sealed class UiState {
        data class Success(val data: List<ProductUi>) : UiState()
        data class Failure(val e: Exception) : UiState()
        object Loading : UiState()
    }
}