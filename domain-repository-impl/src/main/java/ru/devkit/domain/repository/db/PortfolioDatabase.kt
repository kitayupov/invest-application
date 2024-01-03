package ru.devkit.domain.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.devkit.domain.repository.db.data.InvestmentDb

/**
 * @author k.i.tayupov
 */
@Database(entities = [InvestmentDb::class], version = 1)
abstract class PortfolioDatabase : RoomDatabase() {
    abstract fun portfolioDao() : PortfolioDao
}
