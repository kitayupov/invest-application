package ru.devkit.service.data

/**
 * @author k.i.tayupov
 */
data class PortfolioApi(
    val items: List<InvestmentApi>,
) {

    companion object {
        val EMPTY = PortfolioApi(emptyList())
    }
}
