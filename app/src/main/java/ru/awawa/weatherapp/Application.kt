package ru.awawa.weatherapp

import android.app.Application
import android.util.Log
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.awawa.weatherapp.koin.applicationModule
import ru.awawa.weatherapp.koin.viewModelModule
import ru.awawa.weatherapp.persistence.Database


class Application: Application() {

    companion object {
        const val TAG = "Application"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(listOf(applicationModule, viewModelModule))
        }
    }
}