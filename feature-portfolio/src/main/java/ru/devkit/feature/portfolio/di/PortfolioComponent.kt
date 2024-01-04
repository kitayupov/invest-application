package ru.devkit.feature.portfolio.di

import dagger.Component
import ru.devkit.feature.portfolio.PortfolioFragment

/**
 * @author k.i.tayupov
 */
@PortfolioScope
@Component(dependencies = [PortfolioComponentDependencies::class])
interface PortfolioComponent {

    fun inject(fragment: PortfolioFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: PortfolioComponentDependencies): PortfolioComponent
    }
}
