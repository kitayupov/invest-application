package ru.devkit.domain.repository.di

import dagger.Binds
import dagger.Module
import ru.devkit.domain.repository.PortfolioRepository
import ru.devkit.domain.repository.PortfolioRepositoryImpl

/**
 * @author k.i.tayupov
 */
@Module
interface RepositoryModule {

    @Binds
    fun portfolioRepository(impl: PortfolioRepositoryImpl): PortfolioRepository
}
