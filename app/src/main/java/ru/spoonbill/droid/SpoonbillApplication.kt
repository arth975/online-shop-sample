package ru.spoonbill.droid

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.spoonbill.droid.di.viewModelModule
import ru.spoonbill.droid.data.core.di.dataModule

class SpoonbillApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SpoonbillApplication)
            modules(
                listOf(
                    viewModelModule,
                    dataModule,
                )
            )
        }
    }
}