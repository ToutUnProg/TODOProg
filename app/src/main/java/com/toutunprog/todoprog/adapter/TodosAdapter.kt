package com.toutunprog.todoprog.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.toutunprog.todoprog.R
import com.toutunprog.todoprog.model.TodoList

class TodosAdapter(private val myDataset: Array<TodoList>) : RecyclerView.Adapter<TodosAdapter.ViewHolder>() {
    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todos_textview, parent, false) as TextView

        return ViewHolder(textView)
    }

    override fun getItemCount() = myDataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = myDataset[position].title
    }


}