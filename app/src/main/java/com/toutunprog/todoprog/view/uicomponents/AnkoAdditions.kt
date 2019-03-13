package com.toutunprog.todoprog.view.uicomponents

import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView

fun ViewManager.textInputEditText() = textInputEditText {}
inline fun ViewManager.textInputEditText(theme: Int = 0, init: TextInputEditText.() -> Unit) =
	ankoView({ TextInputEditText(it) }, theme, init)

fun ViewManager.textInputLayout() = textInputLayout {}
inline fun ViewManager.textInputLayout(theme: Int = 0, init: TextInputLayout.() -> Unit) =
	ankoView({ TextInputLayout(it) }, theme, init)