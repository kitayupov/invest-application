package ru.devkit.investapplication.app

import android.app.Application
import ru.devkit.investapplication.di.AppComponent
import ru.devkit.investapplication.di.AppModule
import ru.devkit.investapplication.di.DaggerAppComponent

/**
 * @author k.i.tayupov
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .build()
    }
}
