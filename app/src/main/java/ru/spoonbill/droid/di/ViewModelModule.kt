package ru.spoonbill.droid.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.spoonbill.droid.ui.cart.CartViewModel
import ru.spoonbill.droid.ui.favorites.FavoritesViewModel
import ru.spoonbill.droid.ui.home.HomeViewModel
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductSharedViewModel
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductViewModel

val viewModelModule = module {
    viewModel { HomeViewModel(productRepository = get()) }
    viewModel { FavoritesViewModel(productRepository = get()) }
    viewModel { CartViewModel(productRepository = get()) }

    viewModel { SingleProductSharedViewModel() }
    viewModel { SingleProductViewModel(productRepository = get()) }
}