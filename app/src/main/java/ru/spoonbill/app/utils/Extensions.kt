package ru.spoonbill.app.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.button.MaterialButton

fun EditText.showSoftInputKeyboard() {
    val imm = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    requestFocus()
    imm.showSoftInput(
        this,
        InputMethodManager.SHOW_IMPLICIT
    )
}

fun EditText.hideSoftInputKeyboard(activity: Activity) {
    val imm = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    clearFocus()
    imm.hideSoftInputFromWindow(
        activity.currentFocus?.windowToken,
        0
    )
}

fun EditText.handleButtonState(button: MaterialButton, maxLength: Int) {
    addTextChangedListener(object : TextWatcher {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                if (s.length == maxLength && !button.isEnabled)
                    button.isEnabled = true
                else if (button.isEnabled)
                    button.isEnabled = false
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
    })
}