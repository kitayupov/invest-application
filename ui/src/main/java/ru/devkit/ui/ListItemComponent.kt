package ru.devkit.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StyleableRes
import androidx.core.view.isVisible
import ru.devkit.ui.databinding.LayoutListItemBinding

class ListItemComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defaultStyleAttr: Int = 0,
    defaultStyleRes: Int = 0
) : FrameLayout(context, attrs, defaultStyleAttr, defaultStyleRes) {

    private val binding = LayoutListItemBinding.inflate(LayoutInflater.from(context), this, true)

    val nameTextView = binding.nameTextView
    val valueTextView = binding.valueTextView
    val diffTextView = binding.diffTextView
    val signImageView = binding.signImageView

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ListItemComponent)
        val nameText = typedArray.getString(R.styleable.ListItemComponent_name)
        val nameTextSize = typedArray.getTextSize(R.styleable.ListItemComponent_nameTextSize, 20f)
        val valueTextSize = typedArray.getTextSize(R.styleable.ListItemComponent_valueTextSize, 16f)
        val diffTextSize = typedArray.getTextSize(R.styleable.ListItemComponent_diffTextSize, 12f)
        val showDiff = typedArray.getBoolean(R.styleable.ListItemComponent_showDiff, true)
        typedArray.recycle()

        setName(nameText)
        setTextSize(nameTextView, nameTextSize)
        setTextSize(valueTextView, valueTextSize)
        setTextSize(diffTextView, diffTextSize)
        setShowDiff(showDiff)
    }

    fun setName(name: String?) {
        nameTextView.text = name
    }

    fun setData(data: Data) {
        valueTextView.text = data.value
        diffTextView.text = "${data.diffValue} (${data.diffPercentage})"
        diffTextView.setTextColor(data.diffColor ?: diffTextView.currentTextColor)
        data.sign?.let { signImageView.setImageResource(it) } ?: kotlin.run { signImageView.setImageDrawable(null) }
    }

    fun setSign(@DrawableRes icon: Int, animated: Boolean) {
        signImageView.setImageResource(icon)
        if (animated) {
            signImageView.startAnimation(fadeOut)
        }
    }

    private val fadeOut = AlphaAnimation(1f, 0f).apply {
        duration = 2_000
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.signImageView.alpha = 1f
            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.signImageView.alpha = 0f
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }

    private fun setTextSize(textView: TextView, size: Float) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    private fun TypedArray.getTextSize(@StyleableRes attr: Int, defValue: Float): Float {
        return if (hasValue(attr)) {
            return pxToSp(getDimension(attr, defValue))
        } else {
            defValue
        }
    }

    private fun pxToSp(px: Float): Float {
        val density = context.resources.displayMetrics.density
        return px / density
    }

    private fun setShowDiff(showDiff: Boolean) {
        diffTextView.isVisible = showDiff
    }

    data class Data(
        val value: String,
        val diffValue: String,
        val diffPercentage: String,
        val diffColor: Int? = null,
        val sign: Int? = null
    )
}
