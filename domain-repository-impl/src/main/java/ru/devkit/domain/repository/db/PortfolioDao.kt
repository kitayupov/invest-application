package ru.devkit.domain.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.devkit.domain.repository.db.data.InvestmentDb

/**
 * @author k.i.tayupov
 */
@Dao
interface PortfolioDao {

    @Query("SELECT * FROM InvestmentDb")
    suspend fun getAll(): List<InvestmentDb>

    @Query("SELECT * FROM InvestmentDb WHERE symbol LIKE :symbol")
    fun findBySymbol(symbol: String): InvestmentDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(newItems: List<InvestmentDb>)

    @Transaction
    suspend fun deleteAndInsert(newItems: List<InvestmentDb>) {
        deleteAll()
        insert(newItems)
    }

    @Query("DELETE FROM InvestmentDb")
    suspend fun deleteAll()
}
