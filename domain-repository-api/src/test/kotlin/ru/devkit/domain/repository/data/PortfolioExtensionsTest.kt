package ru.devkit.domain.repository.data

import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author k.i.tayupov
 */
class PortfolioExtensionsTest {

    @Test
    fun `should return total value of investments`() {
        val firstInvestment = mock<Investment> { on { value } doReturn 1.0 }
        val secondInvestment = mock<Investment> { on { value } doReturn 2.0 }
        val thirdInvestment = mock<Investment> { on { value } doReturn 3.0 }

        val investments = listOf(firstInvestment, secondInvestment, thirdInvestment)
        val portfolio = Portfolio(investments)

        assertEquals(6.0, portfolio.totalValue)
    }

    @Test
    fun `should return total difference of investments`() {
        val firstInvestment = mock<Investment> { on { difference } doReturn 1.0 }
        val secondInvestment = mock<Investment> { on { difference } doReturn 2.0 }
        val thirdInvestment = mock<Investment> { on { difference } doReturn 3.0 }

        val investments = listOf(firstInvestment, secondInvestment, thirdInvestment)
        val portfolio = Portfolio(investments)

        assertEquals(6.0, portfolio.totalDifference)
    }

    @Test
    fun `should return total initial value of investments`() {
        val firstInvestment = mock<Investment> { on { initialValue } doReturn 1.0 }
        val secondInvestment = mock<Investment> { on { initialValue } doReturn 2.0 }
        val thirdInvestment = mock<Investment> { on { initialValue } doReturn 3.0 }

        val investments = listOf(firstInvestment, secondInvestment, thirdInvestment)
        val portfolio = Portfolio(investments)

        assertEquals(6.0, portfolio.totalInitialValue)
    }
}
