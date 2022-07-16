package ru.spoonbill.droid.data.core.features.product.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.spoonbill.droid.data.core.config.database.SpoonbillDatabase
import ru.spoonbill.droid.data.core.features.product.remote.data_source.ProductRemoteDataSource
import ru.spoonbill.droid.data.core.features.product.remote.data_source.ProductRemoteDataSourceImpl
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepository
import ru.spoonbill.droid.data.core.features.product.repository.ProductRepositoryImpl

internal val productModule = module {
    factory { provideProductDao(database = get()) }
    factory { ProductRemoteDataSourceImpl() } bind ProductRemoteDataSource::class
    factory { ProductRepositoryImpl(remoteDataSource = get(), dao = get()) } bind ProductRepository::class
}

private fun provideProductDao(database: SpoonbillDatabase) = database.productDao()