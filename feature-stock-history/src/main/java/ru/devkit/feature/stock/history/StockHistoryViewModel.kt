package ru.devkit.feature.stock.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.devkit.domain.repository.PortfolioRepository
import ru.devkit.feature.stock.history.data.InvestmentUiModel
import ru.devkit.feature.stock.history.mapper.InvestmentDomainToUiMapper
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class StockHistoryViewModel @Inject constructor(
    private val repository: PortfolioRepository,
    private val mapper: InvestmentDomainToUiMapper,
) : ViewModel() {

    private val _model = MutableStateFlow(InvestmentUiModel.EMPTY)
    val model = _model.asStateFlow()

    private val _ticks = MutableStateFlow(emptyList<Double>())
    val ticks = _ticks.asStateFlow()

    private val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        error.printStackTrace()
    }

    fun attach(symbol: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repository.getInvestment(symbol).collect {
                _model.value = mapper(it)
                _ticks.value = repository.getStockHistory(symbol)
            }
        }
    }
}
