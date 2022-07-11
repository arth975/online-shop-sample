package ru.spoonbill.data.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.spoonbill.data.product.data_source.ProductDataSource
import ru.spoonbill.data.product.data_source.ProductRemoteDataSource
import ru.spoonbill.data.product.repository.ProductRepository
import ru.spoonbill.data.product.repository.ProductRepositoryImpl

val productModule = module {
    single { ProductRemoteDataSource() } bind ProductDataSource::class
    factory { ProductRepositoryImpl(mDataSource = get()) } bind ProductRepository::class
}