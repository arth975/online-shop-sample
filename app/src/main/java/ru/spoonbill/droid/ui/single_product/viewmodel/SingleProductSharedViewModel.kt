package ru.spoonbill.droid.ui.single_product.viewmodel

import androidx.lifecycle.ViewModel
import ru.spoonbill.droid.ui.cart.model.CartProductUi
import ru.spoonbill.droid.ui.home.entity.ProductUi

class SingleProductSharedViewModel : ViewModel() {

    var selectedProduct: ProductUi? = null
}