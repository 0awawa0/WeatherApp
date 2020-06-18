package ru.awawa.weatherapp

import android.app.Application
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.awawa.weatherapp.koin.applicationModule
import ru.awawa.weatherapp.koin.viewModelModule


class Application: Application() {

    companion object {
        const val TAG = "Application"
    }

    override fun onCreate() {
        super.onCreate()

        Picasso.setSingletonInstance(Picasso.Builder(this).build())
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(listOf(applicationModule, viewModelModule))
        }
    }
}