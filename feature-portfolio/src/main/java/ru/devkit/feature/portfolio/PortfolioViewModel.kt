package ru.devkit.feature.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.devkit.domain.repository.PortfolioRepository
import ru.devkit.feature.portfolio.mapper.PortfolioDomainToUiMapper
import ru.devkit.ui.model.PortfolioUiModel
import javax.inject.Inject

class PortfolioViewModel @Inject constructor(
    private val repository: PortfolioRepository,
    private val mapper: PortfolioDomainToUiMapper,
) : ViewModel() {

    private val _model = MutableStateFlow(PortfolioUiModel.EMPTY)
    val model = _model.asStateFlow()

    init {
        viewModelScope.launch {
            repository.data.collect {
                _model.value = mapper(it)
            }
        }
    }
}
