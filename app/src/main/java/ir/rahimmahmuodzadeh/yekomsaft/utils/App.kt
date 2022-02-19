package ir.rahimmahmuodzadeh.yekomsaft.utils

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val module = module {

        }
        startKoin {
            androidContext(this@App)
            modules(module)
        }
    }
}