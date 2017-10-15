package es.dmoral.toasty

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.annotation.CheckResult
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

/**
 * This file is part of Toasty.
 *
 *
 * Toasty is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *
 * Toasty is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *
 * You should have received a copy of the GNU General Public License
 * along with Toasty.  If not, see <http:></http:>//www.gnu.org/licenses/>.
 */

@SuppressLint("InflateParams")
object Toasty {
    @ColorInt
    private var DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF")
    @ColorInt
    private var ERROR_COLOR = Color.parseColor("#D50000")
    @ColorInt
    private var INFO_COLOR = Color.parseColor("#3F51B5")
    @ColorInt
    private var SUCCESS_COLOR = Color.parseColor("#388E3C")
    @ColorInt
    private var WARNING_COLOR = Color.parseColor("#FFA900")
    @ColorInt
    private val NORMAL_COLOR = Color.parseColor("#353A3E")

    private val LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL)
    private var currentTypeface = LOADED_TOAST_TYPEFACE
    private var textSize = 16 // in SP

    private var tintIcon = true

    @CheckResult
    fun normal(context: Context, message: CharSequence): Toast {
        return normal(context, message, Toast.LENGTH_SHORT, null, false)
    }

    @CheckResult
    fun normal(context: Context, message: CharSequence, icon: Drawable): Toast {
        return normal(context, message, Toast.LENGTH_SHORT, icon, true)
    }

    @CheckResult
    fun normal(context: Context, message: CharSequence, duration: Int): Toast {
        return normal(context, message, duration, null, false)
    }

    @CheckResult
    @JvmOverloads
    fun normal(context: Context, message: CharSequence, duration: Int,
               icon: Drawable?, withIcon: Boolean = true): Toast {
        return custom(context, message, icon, NORMAL_COLOR, duration, withIcon, true)
    }

    @CheckResult
    @JvmOverloads
    fun warning(context: Context, message: CharSequence, duration: Int = Toast.LENGTH_SHORT, withIcon: Boolean = true): Toast {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_error_outline_white_48dp),
                WARNING_COLOR, duration, withIcon, true)
    }

    @CheckResult
    @JvmOverloads
    fun info(context: Context, message: CharSequence, duration: Int = Toast.LENGTH_SHORT, withIcon: Boolean = true): Toast {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_info_outline_white_48dp),
                INFO_COLOR, duration, withIcon, true)
    }

    @CheckResult
    @JvmOverloads
    fun success(context: Context, message: CharSequence, duration: Int = Toast.LENGTH_SHORT, withIcon: Boolean = true): Toast {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_check_white_48dp),
                SUCCESS_COLOR, duration, withIcon, true)
    }

    @CheckResult
    @JvmOverloads
    fun error(context: Context, message: CharSequence, duration: Int = Toast.LENGTH_SHORT, withIcon: Boolean = true): Toast {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_clear_white_48dp),
                ERROR_COLOR, duration, withIcon, true)
    }

    @CheckResult
    fun custom(context: Context, message: CharSequence, icon: Drawable,
               duration: Int, withIcon: Boolean): Toast {
        return custom(context, message, icon, -1, duration, withIcon, false)
    }

    @CheckResult
    fun custom(context: Context, message: CharSequence, @DrawableRes iconRes: Int,
               @ColorInt tintColor: Int, duration: Int,
               withIcon: Boolean, shouldTint: Boolean): Toast {
        return custom(context, message, ToastyUtils.getDrawable(context, iconRes),
                tintColor, duration, withIcon, shouldTint)
    }

    @CheckResult
    fun custom(context: Context, message: CharSequence, icon: Drawable?,
               @ColorInt tintColor: Int, duration: Int,
               withIcon: Boolean, shouldTint: Boolean): Toast {
        var icon = icon
        val currentToast = Toast(context)
        val toastLayout = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.toast_layout, null)
        val toastIcon = toastLayout.findViewById(R.id.toast_icon) as ImageView
        val toastTextView = toastLayout.findViewById(R.id.toast_text) as TextView

        val drawableFrame = if (shouldTint)
            ToastyUtils.tint9PatchDrawableFrame(context, tintColor)
        else
            ToastyUtils.getDrawable(context, R.drawable.toast_frame)

        ToastyUtils.setBackground(toastLayout, drawableFrame)

        if (withIcon) {
            if (icon == null)
                throw IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true")
            if (tintIcon)
                icon = ToastyUtils.tintIcon(icon, DEFAULT_TEXT_COLOR)
            ToastyUtils.setBackground(toastIcon, icon)
        } else {
            toastIcon.visibility = View.GONE
        }

        toastTextView.apply {
            setTextColor(DEFAULT_TEXT_COLOR)
            text = message
            typeface = currentTypeface
            setTextSize(TypedValue.COMPLEX_UNIT_SP, Toasty.textSize.toFloat())
        }

        currentToast.apply {
            view = toastLayout
            this.duration = duration
        }

        return currentToast
    }

    class Config private constructor()// avoiding instantiation
    {

        companion object {

            val instance: Config
                @CheckResult
                get() = Config()

            fun reset() {
                Toasty.DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF")
                Toasty.ERROR_COLOR = Color.parseColor("#D50000")
                Toasty.INFO_COLOR = Color.parseColor("#3F51B5")
                Toasty.SUCCESS_COLOR = Color.parseColor("#388E3C")
                Toasty.WARNING_COLOR = Color.parseColor("#FFA900")
                Toasty.currentTypeface = LOADED_TOAST_TYPEFACE
                Toasty.textSize = 16
                Toasty.tintIcon = true
            }
        }

        @ColorInt
        private var DEFAULT_TEXT_COLOR = Toasty.DEFAULT_TEXT_COLOR
        @ColorInt
        private var ERROR_COLOR = Toasty.ERROR_COLOR
        @ColorInt
        private var INFO_COLOR = Toasty.INFO_COLOR
        @ColorInt
        private var SUCCESS_COLOR = Toasty.SUCCESS_COLOR
        @ColorInt
        private var WARNING_COLOR = Toasty.WARNING_COLOR

        private var typeface = Toasty.currentTypeface
        private var textSize = Toasty.textSize

        private var tintIcon = Toasty.tintIcon

        @CheckResult
        fun setTextColor(@ColorInt textColor: Int): Config {
            DEFAULT_TEXT_COLOR = textColor
            return this
        }

        @CheckResult
        fun setErrorColor(@ColorInt errorColor: Int): Config {
            ERROR_COLOR = errorColor
            return this
        }

        @CheckResult
        fun setInfoColor(@ColorInt infoColor: Int): Config {
            INFO_COLOR = infoColor
            return this
        }

        @CheckResult
        fun setSuccessColor(@ColorInt successColor: Int): Config {
            SUCCESS_COLOR = successColor
            return this
        }

        @CheckResult
        fun setWarningColor(@ColorInt warningColor: Int): Config {
            WARNING_COLOR = warningColor
            return this
        }

        @CheckResult
        fun setToastTypeface(typeface: Typeface): Config {
            this.typeface = typeface
            return this
        }

        @CheckResult
        fun setTextSize(sizeInSp: Int): Config {
            this.textSize = sizeInSp
            return this
        }

        @CheckResult
        fun tintIcon(tintIcon: Boolean): Config {
            this.tintIcon = tintIcon
            return this
        }

        fun apply() {
            Toasty.DEFAULT_TEXT_COLOR = DEFAULT_TEXT_COLOR
            Toasty.ERROR_COLOR = ERROR_COLOR
            Toasty.INFO_COLOR = INFO_COLOR
            Toasty.SUCCESS_COLOR = SUCCESS_COLOR
            Toasty.WARNING_COLOR = WARNING_COLOR
            Toasty.currentTypeface = typeface
            Toasty.textSize = textSize
            Toasty.tintIcon = tintIcon
        }
    }
}// avoiding instantiation