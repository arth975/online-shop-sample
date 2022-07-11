package ru.spoonbill.app

import android.app.Application
import org.koin.core.context.startKoin
import ru.spoonbill.app.di.viewModelModule
import ru.spoonbill.data.di.categoryModule
import ru.spoonbill.data.di.productModule
import ru.spoonbill.data.di.storyModule

class SpoonbillApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(storyModule, productModule, viewModelModule, categoryModule))
        }
    }
}