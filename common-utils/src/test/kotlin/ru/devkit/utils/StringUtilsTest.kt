package ru.devkit.utils

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author k.i.tayupov
 */
class StringUtilsTest {

    @Test
    fun `should return formatted currency price`() {
        assertEquals("$ 0,00", formatCurrencyPrice(0))
        assertEquals("$ 3,00", formatCurrencyPrice(3))
        assertEquals("$ 3,10", formatCurrencyPrice(3.1))
        assertEquals("$ 3,14", formatCurrencyPrice(3.14))
        assertEquals("$ 3,14", formatCurrencyPrice(3.1415926))
        assertEquals("$ 3 141,00", formatCurrencyPrice(3_141))
        assertEquals("$ 3 141 592,00", formatCurrencyPrice(3_141_592))
    }

    @Test
    fun `should return formatted price`() {
        assertEquals("0,00", formatPrice(0))
        assertEquals("3,00", formatPrice(3))
        assertEquals("3,10", formatPrice(3.1))
        assertEquals("3,14", formatPrice(3.14))
        assertEquals("3,14", formatPrice(3.1415926))
        assertEquals("3 141,00", formatPrice(3_141))
        assertEquals("3 141 592,00", formatPrice(3_141_592))
    }

    @Test
    fun `should return formatted percentage`() {
        assertEquals("0,00 %", formatPercentage(0))
        assertEquals("3,00 %", formatPercentage(3))
        assertEquals("3,10 %", formatPercentage(3.1))
        assertEquals("3,14 %", formatPercentage(3.14))
        assertEquals("3,14 %", formatPercentage(3.1415926))
    }
}
