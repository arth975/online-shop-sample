package ru.spoonbill.droid.ui.single_product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepository
import ru.spoonbill.droid.ui.home.entity.ProductUi
import ru.spoonbill.droid.mappers.toModel

class SingleProductViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun initialize(productUi: ProductUi?) {
        viewModelScope.launch {
            when (productUi) {
                null -> _uiStateFlow.update { value -> value.copy(errorMessages = listOf("The product shouldn't be null")) }
                else -> _uiStateFlow.update { value ->
                    value.copy(
                        product = productUi,
                        totalPrice = productUi.quantityInCart * productUi.price
                    )
                }
            }
        }
    }

    fun addProductIntoCart() {
        viewModelScope.launch {
            _uiStateFlow.value.product?.toModel()?.let { productRepository.updateProduct(it) }
        }
    }

    fun changeQuantity(quantity: Int) {
        _uiStateFlow.update { value ->
            val product = value.product?.copy(quantityInCart = quantity)
            value.copy(product = product, totalPrice = product!!.quantityInCart * value.product.price)
        }
    }

    fun changeFavoriteStatus(isFavorite: Boolean) {
        viewModelScope.launch {
            _uiStateFlow.value.product?.copy(isFavorite = isFavorite)?.let {
                productRepository.updateProduct(it.toModel())
                _uiStateFlow.update { value -> value.copy(product = it) }
            }
        }
    }

    data class UiState(
        val product: ProductUi? = null,
        val totalPrice: Float = 0f,
        val errorMessages: List<String?> = listOf()
    )
}