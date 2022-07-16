package ru.spoonbill.droid.data.core.config.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.spoonbill.droid.data.core.config.database.SpoonbillDatabase

internal val configModule = module {
    single { SpoonbillDatabase.getInstance(context = get()) } bind SpoonbillDatabase::class
}