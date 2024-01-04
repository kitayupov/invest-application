package ru.devkit.investapplication.app

import android.app.Application
import ru.devkit.common.di.ComponentDependencies
import ru.devkit.common.di.ComponentDependenciesProvider
import ru.devkit.investapplication.di.AppComponent
import ru.devkit.investapplication.di.DaggerAppComponent

/**
 * @author k.i.tayupov
 */
class App : Application(), ComponentDependenciesProvider {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun componentDependencies(): ComponentDependencies {
        return appComponent
    }
}
