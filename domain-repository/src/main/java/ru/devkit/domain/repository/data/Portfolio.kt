package ru.devkit.domain.repository.data

/**
 * @author k.i.tayupov
 */
data class Portfolio(
    val investments: List<Investment>,
) {
    val totalValue = investments.sumOf { it.value }
    val totalDifference = investments.sumOf { it.difference }
    val totalInitialValue = investments.sumOf { it.initialValue }
}
