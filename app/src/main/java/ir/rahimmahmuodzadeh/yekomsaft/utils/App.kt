package ir.rahimmahmuodzadeh.yekomsaft.utils

import android.app.Application
import ir.rahimmahmuodzadeh.yekomsaft.data.dataBase.DataBase
import ir.rahimmahmuodzadeh.yekomsaft.data.repository.RepositoryContact
import ir.rahimmahmuodzadeh.yekomsaft.data.repository.RepositoryImplContact
import ir.rahimmahmuodzadeh.yekomsaft.ui.home.ViewModelHome
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val module = module {
            single { DataBase.getInstance(androidApplication()) }
            factory<RepositoryContact> { RepositoryImplContact(get()) }
            viewModel{ViewModelHome(get())}
        }
        startKoin {
            androidContext(this@App)
            modules(module)
        }
    }
}