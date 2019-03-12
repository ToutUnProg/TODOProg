package com.toutunprog.todoprog.view.uicomponents

import android.content.DialogInterface
import android.support.design.widget.TextInputEditText
import android.view.View
import org.jetbrains.anko.*

class AlertDialogBuilder(
	private val context: AnkoContext<View>,
	private val titleRes: Int,
	private val descriptionRes: Int,
	private val placeholderRes: Int,
	private val callBack: (String) -> Unit
) {

	private lateinit var textInputEditTextVar: TextInputEditText


	fun build(): AlertBuilder<DialogInterface> {

		return with(context) {
			alert {
				customView {
					verticalLayout {
						textView(descriptionRes)
						textInputLayout {
							hint = context.getString(placeholderRes)
							textInputEditTextVar = textInputEditText()
							textInputEditTextVar.singleLine = true

						}
					}
				}
				titleResource = titleRes

				yesButton { callBack(textInputEditTextVar.text.toString()) }
				noButton { }
			}
		}
	}

}