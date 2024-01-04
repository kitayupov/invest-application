package ru.devkit.feature.summary.di

import dagger.Component
import ru.devkit.feature.summary.SummaryFragment

/**
 * @author k.i.tayupov
 */
@SummaryScope
@Component(dependencies = [SummaryComponentDependencies::class])
internal interface SummaryComponent {

    fun inject(fragment: SummaryFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: SummaryComponentDependencies): SummaryComponent
    }
}
