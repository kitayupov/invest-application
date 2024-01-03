package ru.devkit.domain.repository.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author k.i.tayupov
 */
@Entity
data class InvestmentDb(
    @PrimaryKey
    val symbol: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "currentPrice")
    val currentPrice: Double,
    @ColumnInfo(name = "firstPrice")
    val firstPrice: Double,
    @ColumnInfo(name = "lastPrice")
    val lastPrice: Double,
)
