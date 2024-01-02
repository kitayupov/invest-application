package ru.devkit.ui.model

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import ru.devkit.utils.isNegative
import ru.devkit.utils.isPositive

/**
 * @author k.i.tayupov
 */
enum class DiffSign(@ColorInt val color: Int) {
    PLUS(Color.GREEN),
    MINUS(Color.RED),
    NONE(Color.BLACK);

    companion object {
        fun get(value: Double): DiffSign {
            return when {
                value.isNegative() -> MINUS
                value.isPositive() -> PLUS
                else -> NONE
            }
        }
    }
}

enum class LastSign(@DrawableRes val icon: Int) {
    PLUS(R.drawable.ic_arrow_up),
    MINUS(R.drawable.ic_arrow_down),
    NONE(R.drawable.ic_empty);

    companion object {
        fun get(value: Double): LastSign {
            return when {
                value.isNegative() -> MINUS
                value.isPositive() -> PLUS
                else -> NONE
            }
        }
    }
}
