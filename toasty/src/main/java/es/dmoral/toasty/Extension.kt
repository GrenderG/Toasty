package es.dmoral.toasty

import android.content.Context
import android.widget.Toast

/**
 * Created by Anirut Teerabut on 10/15/2017.
 */

fun Any.toastySuccess(context: Context, message: String) {
    Toasty.success(context, message, Toast.LENGTH_SHORT, true).show()
}

fun Any.toastyInfo(context: Context, message: String) {
    Toasty.info(context, message, Toast.LENGTH_SHORT, true).show()
}

fun Any.toastyWarning(context: Context, message: String) {
    Toasty.warning(context, message, Toast.LENGTH_SHORT, true).show()
}

fun Any.toastyError(context: Context, message: String) {
    Toasty.error(context, message, Toast.LENGTH_SHORT, true).show()
}