package ru.spoonbill.droid.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.spoonbill.droid.data.core.features.product.entity.Product
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepository
import ru.spoonbill.droid.ui.cart.CartViewModel.UiEvent.*
import ru.spoonbill.droid.ui.home.entity.ProductUi
import ru.spoonbill.droid.mappers.toModel
import ru.spoonbill.droid.mappers.toUi

class CartViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiEventsFlow = MutableStateFlow<UiEvent>(InitialEvent)
    val uiEventsFlow = _uiEventsFlow.asStateFlow()

    suspend fun cartProductsFlow() = productRepository.getCartProductsFlow().map{ it.map(Product::toUi) }

    fun updateProductQuantity(product: ProductUi) {
        viewModelScope.launch {
            if(product.quantityInCart < 1) {
                deleteProductFromCart(product)
            } else {
                productRepository.updateProduct(product.toModel())
            }
        }
    }

    fun deleteProductFromCart(product: ProductUi) {
        viewModelScope.launch {
            productRepository.updateProduct(product.copy(quantityInCart = 0).toModel())
        }
    }

    fun changeQuantityEvent(product: ProductUi) {
        _uiEventsFlow.value = QuantityChangedEvent(product)
    }

    fun deleteProductEvent(product: ProductUi) {
        _uiEventsFlow.value = DeleteProductEvent(product)
    }

    sealed class UiEvent {
        data class QuantityChangedEvent(val product: ProductUi) : UiEvent()
        data class DeleteProductEvent(val product: ProductUi) : UiEvent()
        object InitialEvent : UiEvent()
    }
}