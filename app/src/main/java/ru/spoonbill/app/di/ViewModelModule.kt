package ru.spoonbill.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.spoonbill.app.ui.catalog.CatalogViewModel
import ru.spoonbill.app.ui.home.HomeViewModel

val viewModelModule = module {
    viewModel { HomeViewModel(mProductRepository = get(), mStoryRepository = get()) }
    viewModel { CatalogViewModel(mProductRepository = get(), mCategoryRepository = get()) }
}