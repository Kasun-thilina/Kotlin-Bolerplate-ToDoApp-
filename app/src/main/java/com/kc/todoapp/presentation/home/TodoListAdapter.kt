package com.kc.todoapp.presentation.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kc.todoapp.R
import com.kc.todoapp.domain.model.DTodo
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoListAdapter(
    var mContext: Context,
    var todoList: MutableList<DTodo>,
) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {
            val todoItem = todoList[position]
            itemView.tv_todo_title.text =
                if (todoItem.title!!.length >= 35) todoItem.title.take(20).trim()
                    .plus("...") else todoItem.title
            itemView.tv_todo_date.text = "7.00 AM"
        }
    }
}