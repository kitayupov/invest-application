package ru.devkit.feature.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.devkit.domain.repository.PortfolioRepository
import ru.devkit.feature.portfolio.mapper.PortfolioDomainToUiMapper
import ru.devkit.feature.portfolio.data.PortfolioUiModel
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
internal class PortfolioViewModel @Inject constructor(
    private val repository: PortfolioRepository,
    private val mapper: PortfolioDomainToUiMapper,
) : ViewModel() {

    private val _model = MutableStateFlow(PortfolioUiModel.EMPTY)
    val model = _model.asStateFlow()

    private val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        error.printStackTrace()
    }

    private var job: Job = Job()

    fun attach() {
        job = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repository.getPortfolio().collect {
                _model.value = mapper(it)
            }
        }
    }

    fun detach() {
        job.cancel()
    }
}
