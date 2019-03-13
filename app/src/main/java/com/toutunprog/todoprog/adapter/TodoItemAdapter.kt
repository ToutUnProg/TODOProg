package com.toutunprog.todoprog.adapter

import android.graphics.Paint
import android.os.CountDownTimer
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.toutunprog.todoprog.R
import com.toutunprog.todoprog.model.TodoItem

class TodoItemAdapter(
	private var myDataset: Array<TodoItem>
) : RecyclerView.Adapter<TodoItemAdapter.ViewHolder>() {

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		val textView = view.findViewById<TextView>(R.id.textView)
		val checkBox = view.findViewById<CheckBox>(R.id.checkbox)

		fun bind(todoItem: TodoItem) {
			textView.text = todoItem.text
			checkBox.setOnCheckedChangeListener { _, isChecked ->
				todoItem.done = isChecked
				if (isChecked) {
//					textView.paintFlags = textView.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
					textView.animateStrikeThrough()
				} else {
					textView.paintFlags = textView.paintFlags.and(Paint.STRIKE_THRU_TEXT_FLAG.inv())
					textView.text = todoItem.text
				}
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.todo_detail_item, parent, false)

		return ViewHolder(view)
	}

	override fun getItemCount() = myDataset.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(myDataset[position])

	}

	fun updateData(dataset: Array<TodoItem>) {
		myDataset = dataset
		notifyDataSetChanged()
	}


}

private fun TextView.animateStrikeThrough() {
	val ANIM_DURATION = 300L
	val length = this.text.length
	val countDownTimer = object : CountDownTimer(ANIM_DURATION, ANIM_DURATION / length) {

		val tv = this@animateStrikeThrough
		val span = SpannableString(tv.text)
		val strikethroughSpan = StrikethroughSpan()

		override fun onFinish() {
			tv.paintFlags = tv.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
		}

		override fun onTick(millisUntilFinished: Long) {
			var endPosition = (((millisUntilFinished - ANIM_DURATION) * -1) / (ANIM_DURATION / length)).toInt()
			endPosition = if (endPosition > length) length else endPosition
			span.setSpan(strikethroughSpan, 0, endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
			tv.text = span

		}

	}
	countDownTimer.start()
}