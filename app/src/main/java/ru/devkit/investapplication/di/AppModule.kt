package ru.devkit.investapplication.di

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * @author k.i.tayupov
 */
@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}
