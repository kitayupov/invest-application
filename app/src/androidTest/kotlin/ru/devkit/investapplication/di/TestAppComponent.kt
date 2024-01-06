package ru.devkit.investapplication.di

import dagger.Component
import ru.devkit.investapplication.MainActivity

/**
 * @author k.i.tayupov
 */
@Component(
    modules = [TestAppModule::class]
)
interface TestAppComponent {

    fun inject(activity: MainActivity)
}
