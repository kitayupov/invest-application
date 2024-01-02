package ru.devkit.investapplication.di

import dagger.Component
import ru.devkit.investapplication.MainActivity

/**
 * @author k.i.tayupov
 */
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}
