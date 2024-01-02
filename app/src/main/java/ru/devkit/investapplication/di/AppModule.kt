package ru.devkit.investapplication.di

import android.content.Context
import dagger.Module

/**
 * @author k.i.tayupov
 */
@Module
class AppModule(private val context: Context) {

    fun provideContext(): Context {
        return context
    }
}
