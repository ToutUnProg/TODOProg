package com.toutunprog.todoprog.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toutunprog.todoprog.R

class MenuTodoBottomSheetDialogFragment<T> :
	TodoBottomSheetDialogFragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.bottom_sheet_modify_todo, container)
	}

}