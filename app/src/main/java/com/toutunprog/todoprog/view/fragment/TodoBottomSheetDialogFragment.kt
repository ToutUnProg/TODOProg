package com.toutunprog.todoprog.view.fragment

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import com.toutunprog.todoprog.R

open class TodoBottomSheetDialogFragment : BottomSheetDialogFragment() {

	override fun getTheme(): Int = R.style.BottomSheetDialogTheme

	override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog =
		BottomSheetDialog(requireContext(), theme)

}