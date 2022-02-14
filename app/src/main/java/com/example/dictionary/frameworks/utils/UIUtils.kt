package com.example.dictionary.frameworks.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import com.example.dictionary.R

fun getStubAlertDialog(context: Context): AlertDialog {
    return getAlertDialog(context, null, null)
}

fun getAlertDialog(context: Context, title: String?, message: String?): AlertDialog {
    return AlertDialog.Builder(context)
        .setTitle(if (!title.isNullOrBlank()) title else context.getString(R.string.dialog_title_stub))
        .setMessage(if (!message.isNullOrBlank()) message else null)
        .setCancelable(true)
        .setPositiveButton(R.string.dialog_button_cancel) { dialog, _ -> dialog.dismiss() }
        .create()
}

class EquilateralImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}